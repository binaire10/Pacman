package fr.univ_amu.game.graphic.engine;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.core.loader.EngineLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowResizeEvent;
import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.render.RenderCommand;
import fr.univ_amu.game.util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Moteur graphique
 */
@EngineLayer
public class GraphicEngine implements Layer {
    private static GraphicEngine instance;
    private final Map<Sprite, QuadEntity> graphicEntities = new HashMap<>();
    private Window window;
    private GraphicLayer layer;

    public static GraphicEngine getEngine() {
        return instance;
    }

    public Window getWindow() {
        return window;
    }

    /**
     * Fonction utilisé à chaque mise à jour de l'écran,fonction qui sera utilisé pour les objets dépendant du temps
     * @param timestep Periode de temps entre 2 mise à jour
     */
    @Override
    public void onUpdate(double timestep) {
        final RenderCommand command = Platform.getRenderCommand();
        computeRender();
        window.swap();
    }

    /**
     * Lorque un évenement est capturé,il est redirigé vers le moteur d'entrée/sortie
     * @param e
     */
    @Override
    public void onEvent(Event e) {
        Utility.dispatch(e, WindowResizeEvent.class, r -> Platform.getRenderCommand().setViewport(0, 0, r.width, r.height));
    }

    /**
     * Fonction iniatilisant la fenetre graphique,tel que sa dimension
     */
    @Override
    public void onAttach() {
        instance = this;
        window = Platform.create_window("Graphic Engine", 720, 400);
        window.make_context();
        layer = ServiceLoader.load(GraphicLayer.class).findFirst().get();
        layer.onAttach();
        window.show();
    }

    @Override
    public void onDetach() {
        layer.onDetach();
    }

    private void computeRender() {
        layer.onBegin(window);
        layer.onRender(new ArrayList<>(graphicEntities.values()));
        layer.onEnd();
    }

    public void setTitle(String title) {
        window.setTitle(title);
    }

    public void show() {
        window.show();
    }

    /**
     * Fonction ajoutant une entité graphique,sous forme rectangulaire
     * @param layer Couche intermédiare possedant des sprites aidant la synchronisation si multi threading utilisé
     * @param sprite Image/Couleur associé au rectangle
     * @param z Profondeur de l'affichage(comprise entre -1 et 1) -1 étant le plus proche de l'utilisateur
     * @return
     */
    public QuadEntity add(Class<? extends Layer> layer, Sprite sprite, float z) {
        QuadEntity entity = new QuadEntity(layer, sprite, z);
        graphicEntities.put(sprite, entity);
        return entity;
    }

    public void remove(Sprite sprite) {
        graphicEntities.remove(sprite).unbind();
    }
}
