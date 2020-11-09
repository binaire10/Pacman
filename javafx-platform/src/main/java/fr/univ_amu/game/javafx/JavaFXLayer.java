package fr.univ_amu.game.javafx;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.core.loader.HardwareLayer;
import fr.univ_amu.game.graphic.engine.GraphicLayer;
import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.javafx.render.JavaFXRenderCommand;
import javafx.scene.canvas.GraphicsContext;

import java.util.Collection;

@HardwareLayer
public class JavaFXLayer implements GraphicLayer {
    GraphicsContext context;

    @Override
    public synchronized void onAttach() {
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
    public void onRender(Collection<QuadEntity> graphicEntity) {
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
}
