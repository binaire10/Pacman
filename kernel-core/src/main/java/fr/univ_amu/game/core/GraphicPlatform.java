package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.render.RenderCommand;
import fr.univ_amu.game.render.Texture2D;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

/**
 * Interface permettant d'avoir acces à des fonction estimé essentiel pour le moteur graphique
 */
public interface GraphicPlatform {
    void processEvent();

    void dispatch(Event event);

    /**
     * Créer une fenetre de taille Width * Height
     * @param title
     * @param width
     * @param height
     * @return
     */
    Window create_window(String title, int width, int height);

    void clear();

    LayerStack<Layer> getLayerStack();

    RenderCommand getRenderCommand();

    /**
     *
     * @param data structure contenant un tableau de float représentant une image
     * @return Texture à l'image des pixel représenté par le Bytebuffer
     */
    Texture2D load_texture(ByteBuffer data);

    Texture2D make_texture(int w, int h);

    void startMainThread(Supplier<Runnable> runnable);
}
