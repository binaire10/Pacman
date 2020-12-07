package fr.univ_amu.game.graphic.engine;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.core.loader.EngineLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowResizeEvent;
import fr.univ_amu.game.graphic.camera.Camera;
import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.render.RenderCommand;
import fr.univ_amu.game.util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

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
        layer.onRender(new ArrayList<>(graphicEntities.values()));
        layer.onEnd();
    }

    public void setTitle(String title) {
        window.setTitle(title);
    }

    public void show() {
        window.show();
    }

    public QuadEntity add(Class<? extends Layer> layer, Sprite sprite, float z) {
        QuadEntity entity = new QuadEntity(layer, sprite, z);
        graphicEntities.put(sprite, entity);
        return entity;
    }

    public void remove(Sprite sprite) {
        var r = graphicEntities.remove(sprite);
        if (r != null)
            r.unbind();
    }

    public void setCamera(Camera camera) {
        layer.setCamera(camera);
    }
}
