package fr.univ_amu.game.pacman;

import fr.univ_amu.game.physics.PhysicEntity;
import fr.univ_amu.game.physics.Shape;

public class Ghost implements PhysicEntity {

    private boolean isVulnerable;
    private float[] position;
    private Shape shape;

    public Ghost(float[] position, Shape shape){
        this.position = position;
        this.isVulnerable = false;
        this.shape = shape;
    }

    @Override
    public void update(double timestep) {

    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    public boolean getVulnerability(){
        return this.isVulnerable;
    }

    public float[] getPosition(){
        return this.position;
    }
}
