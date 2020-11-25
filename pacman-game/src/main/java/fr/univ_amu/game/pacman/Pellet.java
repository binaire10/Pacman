package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.physics.PhysicEntity;

public class Pellet{

    private PhysicEntity physical;
    private QuadEntity graphical;

    public Pellet(PhysicEntity physical, QuadEntity graphical){
        this.physical = physical;
        this.graphical = graphical;
    }

    public PhysicEntity getPhysical() {
        return this.physical;
    }

    public QuadEntity getGraphical() {
        return this.graphical;
    }
}
