package fr.univ_amu.game.core;

import fr.univ_amu.game.beans.Binding;
import fr.univ_amu.game.beans.ObjectProperty;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;
import fr.univ_amu.game.render.Texture2D;

public class Sprite {
    private final ObjectProperty<Point2D> position;
    private final ObjectProperty<Rectangle2D> shape;
    private final ObjectProperty<Texture2D> texture;
    private final ObjectProperty<float[]> color;
    private final ObjectProperty<Point2D> speed;

    public Sprite(Rectangle2D shape, Texture2D texture) {
        this.shape = new ObjectProperty<>(shape);
        this.position = new ObjectProperty<>();
        this.texture = new ObjectProperty<>(texture);
        this.color = new ObjectProperty<>();
        this.speed = new ObjectProperty<>(Point2D.ZERO);

        Binding.bindBidirectional(null, this.shape, null, position, s -> s.p1.sum(s.p2).divide(2), p -> {
            Rectangle2D r = this.shape.getValue();
            return r.moveTo(p.minus(new Point2D(r.width / 2f, r.heigth / 2f)));
        });
    }

    public Sprite(Rectangle2D shape, float[] color) {
        this.shape = new ObjectProperty<>(shape);
        this.position = new ObjectProperty<>();
        this.texture = new ObjectProperty<>();
        this.color = new ObjectProperty<>(color);
        this.speed = new ObjectProperty<>(Point2D.ZERO);

        Binding.bindBidirectional(null, this.shape, null, position, s -> s.p1.sum(s.p2).divide(2), p -> {
            Rectangle2D r = this.shape.getValue();
            return r.moveTo(p.minus(new Point2D(r.width / 2f, r.heigth / 2f)));
        });
    }

    public ObjectProperty<Point2D> getPositionProperty() {
        return position;
    }

    public ObjectProperty<Rectangle2D> getShapeProperty() {
        return shape;
    }

    public ObjectProperty<Texture2D> getTextureProperty() {
        return texture;
    }

    public ObjectProperty<float[]> getColorProperty() {
        return color;
    }

    public ObjectProperty<Point2D> getSpeedProperty() {
        return speed;
    }

    public Point2D getPosition() {
        return position.getValue();
    }

    public void setPosition(Point2D position) {
        this.position.setValue(position);
    }

    public Rectangle2D getShape() {
        return shape.getValue();
    }

    public void setShape(Rectangle2D rectangle) {
        shape.setValue(rectangle);
    }

    public Texture2D getTexture() {
        return texture.getValue();
    }

    public void setTexture(Texture2D texture) {
        this.texture.setValue(texture);
    }

    public float[] getColor() {
        return color.getValue();
    }

    public void setColor(float[] color) {
        this.color.setValue(color);
    }

    public Point2D getSpeed() {
        return speed.getValue();
    }

    public void setSpeed(Point2D speed) {
        this.speed.setValue(speed);
    }
}
