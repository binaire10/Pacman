package fr.univ_amu.game.pacman;

import fr.univ_amu.game.math.Rectangle2D;
import fr.univ_amu.game.physics.CollideListener;
import fr.univ_amu.game.physics.PhysicEntity;

public class Collision implements CollideListener {

    @Override
    public void collideBetween(PhysicEntity p1, Rectangle2D oldObj1, PhysicEntity p2, Rectangle2D oldObj2) {
        // ToDO: Complete the collisions between pacman and ghosts, and pacman and pellets.
    }
}
