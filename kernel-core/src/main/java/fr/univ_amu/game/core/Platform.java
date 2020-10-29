package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.render.*;
import fr.univ_amu.graph.DepthIterator;
import fr.univ_amu.graph.Node;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public final class Platform {
    private static final GraphicPlatform GRAPHIC_PLATFORM = ServiceLoader.load(GraphicPlatform.class).findFirst().orElseGet(() -> null);

    static {
        if (GRAPHIC_PLATFORM != null) {
            var nodes = ServiceLoader.load(Layer.class).stream().map(Node::new).collect(Collectors.toList());
            for (var node : nodes) {
                var classt = node.getValue().type().getAnnotation(RequireLayer.class);
                if (classt != null)
                    for (Class<? extends Layer> require : classt.require()) {
                        for (var child : nodes)
                            if (require.isAssignableFrom(child.getValue().type()))
                                node.add(child);

                    }
            }
            LayerStack stack = GRAPHIC_PLATFORM.getLayerStack();
            new DepthIterator<>(nodes.iterator()).forEachRemaining(c -> stack.pushLayer(c.get()));
        }
    }

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

    public static RenderCommand getRenderCommand() {
        return GRAPHIC_PLATFORM.getRenderCommand();
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
      
    public static Texture2D load_texture(ByteBuffer image) {
        return GRAPHIC_PLATFORM.load_texture(image);
    }

    public static Texture2D make_texture(int w, int h) {
        return GRAPHIC_PLATFORM.make_texture(w, h);
    }
}
