package fr.univ_amu.game.physics;

import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.math.Rectangle2D;

public interface PhysicEntity {
    void update(double timestep);

    Rectangle2D getShape();

    Sprite getSprite();

    void bind();

    void unbind();
}
