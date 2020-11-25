package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.physics.PhysicEntity;

public class PowerPellet extends GiveScore {

    public PowerPellet(PhysicEntity physical, QuadEntity graphical) {
        super(100, physical, graphical);
    }
}
