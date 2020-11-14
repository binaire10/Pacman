package fr.univ_amu.game.physics;

import java.util.ArrayList;
import java.util.List;

import static fr.univ_amu.game.math.VectorUtility.*;

public class PhysicMoveEntity implements PhysicEntity {

    private float[] position;
    private float[] speed;
    private List<PositionListener> positionListeners;
    private Shape shape;

    public void setSpeed(float[] speed) {
        this.speed = speed;
    }

    public void setPosition(float[] position) {
        float[] diffPos = minus(position, this.position);
        this.position = position;
        getShape().applyVector(diffPos);
    }

    public PhysicMoveEntity(float[] position, float[] speed, Shape shape) {
        this.positionListeners = new ArrayList<>();
        this.position = position;
        this.speed = speed;
        this.shape = shape;
    }

    @Override
    public void update(double timestep) {
        float[] vector = product(speed, (float) timestep);
        position = sum(position, vector);
        shape.applyVector(vector);
        changePosition();
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public void addPositionListener(PositionListener positionListener) {
        this.positionListeners.add(positionListener);
    }

    public void removePositionListener(PositionListener positionListener) {
        this.positionListeners.remove(positionListener);
    }

    private void changePosition() {
        for (PositionListener positionListener : positionListeners) {
            positionListener.update(position);
        }
    }
}
