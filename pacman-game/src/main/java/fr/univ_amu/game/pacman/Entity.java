package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.physics.PhysicEntity;

public abstract class Entity {

    private PhysicEntity physical;
    private QuadEntity graphical;

    protected Entity(PhysicEntity physical, QuadEntity graphical){
        this.physical = physical;
        this.graphical = graphical;
    }

    public PhysicEntity getPhysical() {
        return physical;
    }

    public QuadEntity getGraphical() {
        return graphical;
    }

}
