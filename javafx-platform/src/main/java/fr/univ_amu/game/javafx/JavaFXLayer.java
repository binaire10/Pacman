package fr.univ_amu.game.javafx;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.core.loader.HardwareLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.graphic.engine.GraphicLayer;
import fr.univ_amu.game.graphic.entities.GraphicEntity;
import fr.univ_amu.game.javafx.render.JavaFXRenderCommand;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

@HardwareLayer
public class JavaFXLayer extends Application implements GraphicLayer {
    GraphicsContext context;

    @Override
    public synchronized void onAttach() {
    }

    @Override
    public void onEvent(Event e) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onBegin(Window surface) {
        var win = (JavaFxWindow) surface;
        context = win.getContext();
        ((JavaFXRenderCommand) Platform.getRenderCommand()).setFxWindow(win);
        Platform.getRenderCommand().clear();
    }

    @Override
    public void onRender(GraphicEntity graphicEntity) {
//        if (graphicEntity instanceof QuadEntity) {
//            var quad = (QuadEntity) graphicEntity;
//            if (quad.getColor() != null && quad.getTexture() != null) {
//                context.drawImage();
//            }
//        }
//        context.
    }

    @Override
    public void onEnd() {
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
