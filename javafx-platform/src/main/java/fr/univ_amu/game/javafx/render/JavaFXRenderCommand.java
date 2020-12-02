package fr.univ_amu.game.javafx.render;

import fr.univ_amu.game.javafx.JavaFxWindow;
import fr.univ_amu.game.render.RenderCommand;
import javafx.scene.paint.Color;

/**
 * Commande basique pour les rendus à l'écran créant une couche de genericité au pres du moteur de jeu
 */
public class JavaFXRenderCommand implements RenderCommand {
    private JavaFxWindow fxWindow;

    public void setFxWindow(JavaFxWindow fxWindow) {
        this.fxWindow = fxWindow;
    }

    @Override
    public void setViewport(int x, int y, int width, int height) {
    }

    @Override
    public void setClear(float r, float g, float b, float a) {
        fxWindow.getScene().setFill(Color.color(r, g, b, a));
    }

    @Override
    public void clear() {
        fxWindow.getCanvas().getChildren().clear();
    }
}
