package fr.univ_amu.game.physics;

import fr.univ_amu.game.math.Rectangle2D;

public interface CollideListener {
    void collideBetween(PhysicEntity p1, Rectangle2D oldObj1, PhysicEntity p2, Rectangle2D oldObj2);
}
