package fr.univ_amu.game.math;

import java.util.Objects;

/**
 * Classe nous fournissant des vecteur 3 dimension(point 3d) ainsi que des outils pour les manipuler
 */
public class Point3D {
    public static final Point3D ZERO = new Point3D(0, 0, 0);
    public final float x;
    public final float y;
    public final float z;

    public Point3D(Point2D p, float z) {
        this.x = p.x;
        this.y = p.y;
        this.z = z;
    }

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point3D min(Point3D a, Point3D b) {
        return new Point3D(
                Math.min(a.x, b.x),
                Math.min(a.y, b.y),
                Math.min(a.z, b.z)
        );
    }

    public static Point3D max(Point3D a, Point3D b) {
        return new Point3D(
                Math.max(a.x, b.x),
                Math.max(a.y, b.y),
                Math.max(a.z, b.z)
        );
    }

    public Point3D minus(Point3D p) {
        return new Point3D(x - p.x, y - p.y, z - p.z);
    }

    public Point3D sum(Point3D p) {
        return new Point3D(x + p.x, y + p.y, z + p.z);
    }

    public float dot(Point3D p) {
        return x * p.x + y * p.y + z * p.z;
    }

    public Point3D multiply(Point3D p) {
        return new Point3D(x * p.x, y * p.y, z * p.z);
    }

    public Point3D multiply(float v) {
        return new Point3D(x * v, y * v, z + v);
    }

    public Point3D divide(Point3D p) {
        return new Point3D(x / p.x, y / p.y, z / p.z);
    }

    public Point3D divide(float v) {
        return new Point3D(x / v, y / v, z / v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return Float.compare(point3D.x, x) == 0 &&
                Float.compare(point3D.y, y) == 0 &&
                Float.compare(point3D.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
