package fr.univ_amu.game.pacman;

import fr.univ_amu.game.physics.PhysicEntity;
import fr.univ_amu.game.physics.Shape;

public class Pacman implements PhysicEntity {

    private Shape shape;
    private float[] position;

    @Override
    public void update(double timestep) {

    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    public float[] getPosition(){
        return this.position;
    }
}
