package fr.univ_amu.game.javafx;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.core.loader.HardwareLayer;
import fr.univ_amu.game.graphic.camera.Camera;
import fr.univ_amu.game.graphic.camera.OrthographicCamera;
import fr.univ_amu.game.graphic.engine.GraphicLayer;
import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.javafx.render.JavaFXTexture2D;
import fr.univ_amu.game.math.MatrixUtility;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Point3D;
import fr.univ_amu.game.math.VectorUtility;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@HardwareLayer
public class JavaFXLayer implements GraphicLayer {
    Group render;
    private Camera camera;
    private float width;
    private float height;
    private List<Node> children;

    @Override
    public void onAttach() {
        camera = new OrthographicCamera(1f);
    }

    @Override
    public void onDetach() {
    }

    @Override
    public void onBegin(Window surface) {
        var win = (JavaFxWindow) surface;
        render = win.canvas;
//        context = win.getContext();
//        ((JavaFXRenderCommand) Platform.getRenderCommand()).setFxWindow(win);
        Platform.getRenderCommand().clear();
        if (camera instanceof OrthographicCamera) {
            float ratio = (float) surface.getWidth() / surface.getHeight();
            ((OrthographicCamera) camera).setRatio(ratio);
        }
        width = surface.getWidth();
        height = surface.getHeight();
    }

    @Override
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void onRender(Collection<QuadEntity> graphicEntity) {
        children = new ArrayList<>(graphicEntity.size());
        float[] view = camera.getMatrix();
        for (QuadEntity entity : graphicEntity) {
            final Point2D size = entity.getSize();
            final Point3D position = entity.getPosition();
            final float[] color = entity.getColor();
            float[] matrix = {
                    size.x, 0, 0, position.x,
                    0, size.y, 0, position.y,
                    0, 0, 1, position.z,
                    0, 0, 0, 1
            };
            float[] transform = MatrixUtility.dot_product(view, matrix, 4);
            float[] p0 = VectorUtility.dot_product(new float[]{-0.5f, -0.5f, 0f, 1f}, transform, 4);
            float[] p1 = VectorUtility.dot_product(new float[]{0.5f, 0.5f, 0f, 1f}, transform, 4);

            float[] p0Screen = {(1f - p0[0] / p0[3]) * width / 2f, (1f - p0[1] / p0[3]) * height / 2f};
            float[] p1Screen = {(1f - p1[0] / p0[3]) * width / 2f, (1f - p1[1] / p0[3]) * height / 2f};

            Rectangle rectangle = new Rectangle();
            rectangle.setX(p0Screen[0]);
            rectangle.setY(p1Screen[1]);
            rectangle.setTranslateZ(position.z);
            rectangle.setWidth(p1Screen[0] - p0Screen[0]);
            rectangle.setHeight(p0Screen[1] - p1Screen[1]);
            if (entity.getTexture() != null)
                rectangle.setFill(new ImagePattern(((JavaFXTexture2D) entity.getTexture()).getImage()));
            else
                rectangle.setFill(color == null ? Color.WHITE : Color.color(color[0], color[1], color[2], color[3]));
            rectangle.setStrokeWidth(0);
            children.add(rectangle);
        }
    }

    @Override
    public void onEnd() {
        children.sort(Comparator.comparingDouble(Node::getTranslateZ).reversed());
        render.getChildren().addAll(children);
    }
}
