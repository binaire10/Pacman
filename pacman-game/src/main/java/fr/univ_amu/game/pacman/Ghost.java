package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.physics.PhysicEntity;

public class Ghost {

    private boolean isVulnerable;
    private PhysicEntity physical;
    private QuadEntity graphical;

    public Ghost(PhysicEntity physical, QuadEntity graphical){
        this.isVulnerable = false;
        this.physical = physical;
        this.graphical = graphical;
    }

    public boolean getVulnerability() {
        return this.isVulnerable;
    }

    public PhysicEntity getPhysical() {
        return this.physical;
    }

    public QuadEntity getGraphical() {
        return this.graphical;
    }
}

