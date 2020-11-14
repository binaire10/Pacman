package fr.univ_amu.game.physics;

public interface PhysicEntity {
    void update(double timestep);
    Shape getShape();
}
