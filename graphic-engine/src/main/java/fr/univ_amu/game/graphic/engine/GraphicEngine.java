package fr.univ_amu.game.graphic.engine;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.UpdatableLayer;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.core.loader.EngineLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowResizeEvent;
import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.render.RenderCommand;
import fr.univ_amu.game.util.Utility;

import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

@EngineLayer
public class GraphicEngine implements UpdatableLayer {
    private static GraphicEngine instance;
    private final List<QuadEntity> graphicEntities = new CopyOnWriteArrayList<>();
    private Window window;
    private GraphicLayer layer;

    public static GraphicEngine getEngine() {
        return instance;
    }

    @Override
    public void onUpdate(double timestep) {
        final RenderCommand command = Platform.getRenderCommand();
        computeRender();
        window.swap();
    }

    @Override
    public void onEvent(Event e) {
        Utility.dispatch(e, WindowResizeEvent.class, r -> Platform.getRenderCommand().setViewport(0, 0, r.width, r.height));
    }

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
        layer.onRender(graphicEntities);
        layer.onEnd();
    }

    public void setTitle(String title) {
        window.setTitle(title);
    }

    public void show() {
        window.show();
    }

    public List<QuadEntity> getGraphicEntities() {
        return graphicEntities;
    }
}
