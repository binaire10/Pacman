package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.physics.PhysicEntity;

public class Pellet extends GiveScore{

    public Pellet(PhysicEntity physical, QuadEntity graphical){
        super(10, physical, graphical);
    }
}
