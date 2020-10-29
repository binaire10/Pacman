package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.render.*;

import java.util.Map;
import java.util.ServiceLoader;

public final class Platform {
    private static final GraphicPlatform GRAPHIC_PLATFORM = ServiceLoader.load(GraphicPlatform.class).findFirst().orElseGet(() -> null);

    public static IndexBuffer make_index(int[] index) {
        return GRAPHIC_PLATFORM.make_index(index);
    }

    public static VertexBuffer make_buffer(float[] data) {
        return GRAPHIC_PLATFORM.make_buffer(data);
    }

    public static VertexBuffer make_buffer(int capacity) {
        return GRAPHIC_PLATFORM.make_buffer(capacity);
    }

    public static VertexArray create_vertexArray() {
        return GRAPHIC_PLATFORM.create_vertexArray();
    }

    public static Material create_material(Map<ShaderType, String> shader) {
        return GRAPHIC_PLATFORM.create_material(shader);
    }

    public static Window create_window(String title, int width, int height) {
        return GRAPHIC_PLATFORM.create_window(title, width, height);
    }

    public static GraphicPlatform getGraphicPlatform() {
        return GRAPHIC_PLATFORM;
    }

    public static void dispatch(Event event) {
        GRAPHIC_PLATFORM.dispatch(event);
    }

    public static void processEvent() {
        GRAPHIC_PLATFORM.processEvent();
    }

    public static void clear() {
        GRAPHIC_PLATFORM.clear();
    }

    public static LayerStack getLayerStack() {
        return GRAPHIC_PLATFORM.getLayerStack();
    }
}
