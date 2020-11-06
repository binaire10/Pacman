package fr.univ_amu.game.javafx;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class JavaFxWindow implements Window {
    private final String title;
    private final int width;
    private final int height;
    Canvas canvas;
    private Stage stage;
    private Scene scene;

    public JavaFxWindow(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return (int) canvas.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) canvas.getHeight();
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
        Group root = new Group();
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        stage.setTitle(title);
        stage.setOnCloseRequest((e) -> Platform.dispatch(new WindowCloseEvent(this)));
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void resize(int width, int height) {
        canvas.resize(width, height);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getContext() {
        return canvas.getGraphicsContext2D();
    }

    @Override
    public void setTitle(CharSequence title) {
        stage.setTitle(title.toString());
    }
}
