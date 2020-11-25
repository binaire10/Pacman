package fr.univ_amu.game.graphic.entities;

import fr.univ_amu.game.beans.Binding;
import fr.univ_amu.game.beans.ChangeValueListener;
import fr.univ_amu.game.beans.ObjectProperty;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.graphic.engine.GraphicEngine;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Point3D;
import fr.univ_amu.game.math.Rectangle2D;
import fr.univ_amu.game.render.Texture2D;

import java.util.Arrays;

public class QuadEntity {
    private final ObjectProperty<float[]> color;
    private final ObjectProperty<Texture2D> texture;
    private final ObjectProperty<Point3D> position;
    private final ObjectProperty<Point2D> size;

    private final ChangeValueListener<Texture2D> textureListener;
    private final ChangeValueListener<float[]> colorListener;
    private final ChangeValueListener<Rectangle2D> sizeListener;
    private final ChangeValueListener<Point2D> positionListener;

    public QuadEntity(Class<? extends Layer> source, Sprite sprite, float z) {
        this.color = new ObjectProperty<>();
        this.texture = new ObjectProperty<>();
        this.size = new ObjectProperty<>();
        this.position = new ObjectProperty<>();

        this.textureListener = Binding.bind(source, sprite.getTextureProperty(), GraphicEngine.class, texture);
        this.colorListener = Binding.bind(source, sprite.getColorProperty(), GraphicEngine.class, color);
        this.positionListener = Binding.bind(source, sprite.getPositionProperty(), GraphicEngine.class, position, p -> new Point3D(p, z));
        this.sizeListener = Binding.bind(source, sprite.getShapeProperty(), GraphicEngine.class, size, s -> new Point2D(s.width, s.heigth));
    }

    public QuadEntity(Point3D position, Point2D size, float[] color, Texture2D texture) {
        this.color = new ObjectProperty<>(color);
        this.texture = new ObjectProperty<>(texture);
        this.size = new ObjectProperty<>(size);
        this.position = new ObjectProperty<>(position);

        this.textureListener = null;
        this.colorListener = null;
        this.positionListener = null;
        this.sizeListener = null;
    }

    public QuadEntity(Point3D position, Point2D size, Texture2D texture) {
        this(position, size, null, texture);
    }

    public QuadEntity(Point3D position, Point2D size, float[] color) {
        this(position, size, color, null);
    }

    public float[] getColor() {
        return color.getValue();
    }

    public void setColor(float[] color) {
        this.color.setValue(Arrays.copyOf(color, color.length));
    }

    public Texture2D getTexture() {
        return texture.getValue();
    }

    public void setTexture(Texture2D texture) {
        this.texture.setValue(texture);
    }

    public Point3D getPosition() {
        return position.getValue();
    }

    public void setPosition(Point3D position) {
        this.position.setValue(position);
    }

    public Point2D getSize() {
        return size.getValue();
    }

    public ObjectProperty<Point3D> getPositionProperty() {
        return position;
    }

    private void bind(Class<? extends Layer> source, Sprite sprite, float z) {
    }
}
