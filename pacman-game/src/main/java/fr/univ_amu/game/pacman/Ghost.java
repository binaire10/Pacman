package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.physics.PhysicEntity;

public class Ghost {

    private boolean isVulnerable;
    private PhysicEntity physical;
    private QuadEntity graphical;

    public Ghost(){
        this.isVulnerable = false;
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

