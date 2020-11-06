package fr.univ_amu.game.core;

import fr.univ_amu.game.core.loader.*;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.render.*;
import fr.univ_amu.game.util.Utility;
import fr.univ_amu.graph.DepthIterator;
import fr.univ_amu.graph.Node;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Platform {
    private static final GraphicPlatform GRAPHIC_PLATFORM = ServiceLoader.load(GraphicPlatform.class).findFirst().orElseGet(() -> null);
    private static boolean isRunning;
    private static final CommandExecutor mainCommandExecutor = new CommandExecutor();

    public static CommandExecutor getMainCommandExecutor() {
        return mainCommandExecutor;
    }

    public static <T extends Layer> Stream<T> load_layers(ServiceLoader<T> service) {
        var nodes = service.stream().map(Node::new).sorted(Comparator.comparingInt(c -> evaluate(c.getValue().type()))).collect(Collectors.toList());
        for (var node : nodes) {
            var classt = node.getValue().type().getAnnotation(RequireLayer.class);
            if (classt != null)
                for (Class<? extends Layer> require : classt.require())
                    for (var child : nodes)
                        if (require.isAssignableFrom(child.getValue().type()))
                            node.add(child);
        }
        return Utility.iteratorToStream(new DepthIterator<>(nodes.iterator()), nodes.size()).map(ServiceLoader.Provider::get);
    }

    public static void initialise() {
        isRunning = true;
        if (GRAPHIC_PLATFORM != null)
            GRAPHIC_PLATFORM.getLayerStack().pushLayer(load_layers(ServiceLoader.load(MainLayer.class)).toArray(MainLayer[]::new));
    }

    private static <T> int evaluate(Class<T> tClass) {
        if (tClass.isAnnotationPresent(HardwareLayer.class))
            return -3;
        if (tClass.isAnnotationPresent(EngineLayer.class))
            return -2;
        if (tClass.isAnnotationPresent(GameplayLayer.class))
            return -1;
        if (tClass.isAnnotationPresent(UserLayer.class))
            return tClass.getAnnotation(UserLayer.class).layer();
        return Integer.MAX_VALUE;
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

    public static LayerStack<MainLayer> getLayerStack() {
        return GRAPHIC_PLATFORM.getLayerStack();
    }

    public static Texture2D load_texture(ByteBuffer image) {
        return GRAPHIC_PLATFORM.load_texture(image);
    }

    public static Texture2D make_texture(int w, int h) {
        return GRAPHIC_PLATFORM.make_texture(w, h);
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static void shutdown() {
        isRunning = false;
    }

    public static void postTaskOnMain(FutureTask<?> task) {
        GRAPHIC_PLATFORM.postTaskOnMain(task);
    }
}
