package fr.univ_amu.game.physics;

import fr.univ_amu.game.beans.Binding;
import fr.univ_amu.game.beans.ChangeValueListener;
import fr.univ_amu.game.beans.ObjectProperty;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class PhysicMoveEntity implements PhysicEntity {
    private final Sprite sprite;
    private final ObjectProperty<Rectangle2D> shape;
    private final ObjectProperty<Point2D> speed;

    private final ChangeValueListener<Rectangle2D> shapeListener;
    private final ChangeValueListener<Point2D> speedListener;

    public PhysicMoveEntity(Class<? extends Layer> source, Sprite sprite) {
        this.sprite = sprite;
        this.speed = new ObjectProperty<>();
        this.shape = new ObjectProperty<>();

        this.shapeListener = Binding.bindBidirectional(source, sprite.getShapeProperty(), PhysicLayer.class, shape);
        this.speedListener = Binding.bindBidirectional(source, sprite.getSpeedProperty(), PhysicLayer.class, speed);
    }

    public PhysicMoveEntity(Point2D speed, Rectangle2D shape) {
        this.sprite = null;
        this.speed = new ObjectProperty<>(speed);
        this.shape = new ObjectProperty<>(shape);

        this.shapeListener = null;
        this.speedListener = null;
    }

    public void setSpeed(Point2D speed) {
        this.speed.setValue(speed);
    }

    public void setPosition(Point2D position) {
        shape.setValue(shape.getValue().moveTo(position));
    }

    @Override
    public void update(double timestep) {
        shape.setValue(shape.getValue().applyVector(speed.getValue().multiply((float) timestep)));
    }

    @Override
    public Rectangle2D getShape() {
        return shape.getValue();
    }

    public void setShape(Rectangle2D shape) {
        this.shape.setValue(shape);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public ObjectProperty<Rectangle2D> getShapeProperty() {
        return shape;
    }

    @Override
    public void bind() {
        shape.setValue(sprite.getShape());
        sprite.getShapeProperty().addListener(shapeListener);
        shape.addListener(shapeListener);

        speed.setValue(sprite.getSpeed());
        sprite.getSpeedProperty().addListener(speedListener);
        speed.addListener(speedListener);
    }

    @Override
    public void unbind() {
        sprite.getShapeProperty().removeListener(shapeListener);
        shape.removeListener(shapeListener);
        sprite.getSpeedProperty().removeListener(speedListener);
        speed.removeListener(speedListener);
    }
}
