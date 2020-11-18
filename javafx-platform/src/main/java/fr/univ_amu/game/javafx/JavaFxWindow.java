package fr.univ_amu.game.javafx;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.javafx.render.JavaFXRenderCommand;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFxWindow implements Window {
    private final Stage stage;
    private final Scene scene;
    Group canvas;

    public JavaFxWindow(String title, int width, int height) {
        canvas = new Group();
        stage = new Stage();
        scene = new Scene(canvas, width, height, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setOnCloseRequest((e) -> Platform.dispatch(new WindowCloseEvent(this)));
    }

    @Override
    public int getWidth() {
        return (int) scene.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) scene.getHeight();
    }

    @Override
    public boolean isClose() {
        return !stage.isShowing();
    }

    @Override
    public void swap() {
    }

    @Override
    public void make_context() {
        ((JavaFXRenderCommand) Platform.getRenderCommand()).setFxWindow(this);
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void resize(int width, int height) {
        canvas.resize(width, height);
    }

    public Group getCanvas() {
        return canvas;
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void setTitle(CharSequence title) {
        stage.setTitle(title.toString());
    }
}