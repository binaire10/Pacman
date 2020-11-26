package fr.univ_amu.game.math;

import java.util.Objects;

public class Point2D {
    public static final Point2D ZERO = new Point2D(0, 0);
    public final float x;
    public final float y;

    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Point2D min(Point2D a, Point2D b) {
        if (a.x <= b.x && a.y <= b.y)
            return a;
        if (b.x <= a.x && b.y <= a.y)
            return b;
        return new Point2D(
                Math.min(a.x, b.x),
                Math.min(a.y, b.y)
        );
    }

    public static Point2D max(Point2D a, Point2D b) {
        if (a.x <= b.x && a.y <= b.y)
            return b;
        if (b.x <= a.x && b.y <= a.y)
            return a;
        return new Point2D(
                Math.max(a.x, b.x),
                Math.max(a.y, b.y)
        );
    }

    public Point2D minus(Point2D p) {
        return new Point2D(x - p.x, y - p.y);
    }

    public Point2D sum(Point2D p) {
        return new Point2D(x + p.x, y + p.y);
    }

    public float dot(Point2D p) {
        return x * p.x + y * p.y;
    }

    public Point2D multiply(Point2D p) {
        return new Point2D(x * p.x, y * p.y);
    }

    public Point2D multiply(float v) {
        return new Point2D(x * v, y * v);
    }

    public Point2D divide(Point2D p) {
        return new Point2D(x / p.x, y / p.y);
    }

    public Point2D divide(float v) {
        return new Point2D(x / v, y / v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Float.compare(point2D.x, x) == 0 &&
                Float.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
