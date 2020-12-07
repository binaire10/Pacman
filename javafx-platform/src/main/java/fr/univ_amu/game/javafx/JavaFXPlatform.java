package fr.univ_amu.game.javafx;

import fr.univ_amu.game.core.GraphicPlatform;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.LayerStack;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.javafx.render.JavaFXRenderCommand;
import fr.univ_amu.game.javafx.render.JavaFXTexture2D;
import fr.univ_amu.game.render.RenderCommand;
import fr.univ_amu.game.render.Texture2D;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

/**
 * Classe initialisant et reunissant nos differentes classe nous permettant de créer l'abstraction lié à JavaFX
 */
public class JavaFXPlatform implements GraphicPlatform {
    private final LayerStack<Layer> layers = new LayerStack<>();
    private final JavaFXRenderCommand renderCommand = new JavaFXRenderCommand();

    @Override
    public void processEvent() {

    }

    @Override
    public void dispatch(Event event) {
        for (Layer layer : layers) {
            layer.onEvent(event);
            if (event.isHandle())
                break;
        }
    }

    @Override
    public Window create_window(String title, int width, int height) {
        return new JavaFxWindow(title, width, height);
    }

    @Override
    public void clear() {
        layers.clear();
    }

    @Override
    public LayerStack<Layer> getLayerStack() {
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
    public void startMainThread(Supplier<Runnable> runnable) {
        Platform.startup(() -> {
            fr.univ_amu.game.core.Platform.initialise();
            var main = runnable.get();
            new AnimationTimer() {
                @Override
                public void handle(long l) {
                    main.run();
                }
            }.start();
        });
    }

    @Override
    public void shutdown() {
        Platform.exit();
    }
}
