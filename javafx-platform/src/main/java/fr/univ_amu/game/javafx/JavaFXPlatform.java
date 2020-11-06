package fr.univ_amu.game.javafx;

import fr.univ_amu.game.core.*;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.graphic.engine.GraphicEngineLayer;
import fr.univ_amu.game.javafx.render.JavaFXRenderCommand;
import fr.univ_amu.game.javafx.render.JavaFXTexture2D;
import fr.univ_amu.game.render.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.FutureTask;

public class JavaFXPlatform implements GraphicPlatform {
    private final LayerStack<MainLayer> layers = new LayerStack<>();
    private final JavaFXRenderCommand renderCommand = new JavaFXRenderCommand();

    @Override
    public void processEvent() {

    }

    @Override
    public void dispatch(Event event) {
        GraphicEngineLayer.getEngine().postEvent(event);
        fr.univ_amu.game.core.Platform.getMainCommandExecutor().add(new FutureTask<>(() -> {
            for (MainLayer layer : layers) {
                layer.onEvent(event);
                if (event.isHandle())
                    break;
            }
            return null;
        }));
    }

    @Override
    public Window create_window(String title, int width, int height) {
        return new JavaFxWindow(title, width, height);
    }

    @Override
    public IndexBuffer make_index(int[] index) {
        throw new UnsupportedOperationException("JavaFX doesn't provide index buffer");
    }

    @Override
    public VertexBuffer make_buffer(float[] data) {
        throw new UnsupportedOperationException("JavaFX doesn't provide vertex buffer");
    }

    @Override
    public VertexBuffer make_buffer(int capacity) {
        throw new UnsupportedOperationException("JavaFX doesn't provide vertex buffer");
    }

    @Override
    public VertexArray create_vertexArray() {
        throw new UnsupportedOperationException("JavaFX doesn't provide vertex array");
    }

    @Override
    public Material create_material(Map<ShaderType, String> shader) {
        throw new UnsupportedOperationException("JavaFX doesn't provide material");
    }

    @Override
    public void clear() {
        layers.clear();
    }

    @Override
    public LayerStack<MainLayer> getLayerStack() {
        return layers;
    }

    @Override
    public RenderCommand getRenderCommand() {
        return renderCommand;
    }

    @Override
    public Texture2D load_texture(ByteBuffer data) {
        return new JavaFXTexture2D(data);
    }

    @Override
    public Texture2D make_texture(int w, int h) {
        return new JavaFXTexture2D(w, h);
    }

    @Override
    public AutoCloseable startGraphicEngine(Engine runnable) {
        Platform.startup(() -> {
            runnable.initialize();
            new AnimationTimer() {
                @Override
                public void handle(long l) {
                    runnable.one_step();
                    if (runnable.isStop()) {
                        stop();
                        runnable.release();
                    }
                }
            }.start();
        });
        return new AutoCloseable() {
            @Override
            public void close() throws Exception {
                Platform.exit();
            }
        };
    }
}
