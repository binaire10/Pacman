package fr.univ_amu.game.physics;

import java.util.ArrayList;
import java.util.List;

import static fr.univ_amu.game.math.VectorUtility.*;

public class PhysicMoveEntity implements PhysicEntity {

    private float[] position;
    private float[] speed;
    private List<PositionListener> positionListeners;

    public PhysicMoveEntity(float[] position, float[] speed) {
        this.positionListeners = new ArrayList<>();
        this.position = position;
        this.speed = speed;
    }

    @Override
    public void update(double timestep) {
        position = sum(position, product(speed, (float) timestep));
        changePosition();
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
