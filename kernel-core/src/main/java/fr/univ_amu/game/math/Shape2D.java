package fr.univ_amu.game.math;

public interface Shape2D {
    boolean isIntersect(Shape2D anotherShape);

    Shape2D moveTo(Point2D position);

    Shape2D applyVector(Point2D vector);

    Rectangle2D getBound();
}
