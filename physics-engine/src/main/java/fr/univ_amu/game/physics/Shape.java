package fr.univ_amu.game.physics;

public interface Shape {
    boolean isCollided(Shape anotherShape);
    void applyVector(float[] vector);
}
