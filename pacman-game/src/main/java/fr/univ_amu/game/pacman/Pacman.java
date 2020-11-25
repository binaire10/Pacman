package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.physics.PhysicEntity;

public class Pacman{

    private int life;
    private PhysicEntity physical;
    private QuadEntity graphical;

    public Pacman(PhysicEntity physical, QuadEntity graphical, int life){
        this.physical = physical;
        this.graphical = graphical;
        this.life = life;
    }

    public PhysicEntity getPhysical() {
        return this.physical;
    }

    public QuadEntity getGraphical() {
        return this.graphical;
    }

    public int getLife(){
        return this.life;
    }

    public void decreaseLife(int amountToDecrease){
        if(amountToDecrease > this.life){
            this.life = 0;
            return;
        }
        this.life -= amountToDecrease;
    }
}