package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.physics.PhysicEntity;

public class Ghost extends GiveScore {

    private boolean isVulnerable;

    public Ghost(PhysicEntity physical, QuadEntity graphical) {
        super(500, physical, graphical);
        this.isVulnerable = false;
    }

    public boolean getVulnerability() {
        return this.isVulnerable;
    }
}
