package fr.univ_amu.game.pacman;

import fr.univ_amu.game.physics.PhysicEntity;
import fr.univ_amu.game.physics.Shape;

public class Pacman implements PhysicEntity {

    private Shape shape;
    private float[] position;
    private short life; // Uses a short because it won't be a high number

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

    public short getLife(){
        return this.life;
    }

    public void decreaseLife(short amountToDecrease){
        if(amountToDecrease > this.life){
            this.life = 0;
            return;
        }
        this.life -= amountToDecrease;
    }
}
