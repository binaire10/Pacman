package fr.univ_amu.game.javafx.render;

import fr.univ_amu.game.javafx.JavaFxWindow;
import fr.univ_amu.game.render.RenderCommand;
import fr.univ_amu.game.render.VertexArray;
import javafx.scene.paint.Color;

public class JavaFXRenderCommand implements RenderCommand {
    private Color clearColor = Color.BLACK;
    private JavaFxWindow fxWindow;

    public void setFxWindow(JavaFxWindow fxWindow) {
        this.fxWindow = fxWindow;
    }

    @Override
    public void setViewport(int x, int y, int width, int height) {
    }

    @Override
    public void drawElements(VertexArray vertexArray, int count) {
        throw new UnsupportedOperationException("draw of vertex array unimplemented");
    }

    @Override
    public void setClear(float r, float g, float b, float a) {
        clearColor = new Color(r, g, b, a);
    }

    @Override
    public void clear() {
        var context = fxWindow.getContext();
        context.setFill(clearColor);
        fxWindow.getContext().fillRect(0, 0, fxWindow.getWidth(), fxWindow.getHeight());
    }
}
