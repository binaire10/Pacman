package fr.univ_amu.game.math;

import java.util.Objects;

public class Rectangle2D implements Shape2D {
    public final Point2D p1;
    public final Point2D p2;
    public final float width;
    public final float heigth;

    public Rectangle2D(Point2D p1, Point2D p2) {
        this.p1 = Point2D.min(p1, p2);
        this.p2 = Point2D.max(p1, p2);
        this.width = this.p2.x - this.p1.x;
        this.heigth = this.p2.y - this.p1.y;
    }

    public Rectangle2D(Point2D p1, float width, float heigth) {
        this.p1 = p1;
        this.p2 = new Point2D(p1.x + width, p1.y + heigth);
        this.width = width;
        this.heigth = heigth;
    }

    @Override
    public boolean isIntersect(Shape2D anotherShape) {
        if (anotherShape instanceof Rectangle2D) {
            Rectangle2D rec2 = (Rectangle2D) anotherShape;
            return (this.p2.x >= rec2.p1.x &&
                    this.p2.y >= rec2.p1.y &&
                    rec2.p2.x >= this.p1.x &&
                    rec2.p2.y >= this.p1.y);
        }
        return false;
    }

    @Override
    public Rectangle2D moveTo(Point2D position) {
        return new Rectangle2D(position, width, heigth);
    }

    @Override
    public Rectangle2D applyVector(Point2D vector) {
        return new Rectangle2D(p1.sum(vector), width, heigth);
    }

    @Override
    public Rectangle2D getBound() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle2D that = (Rectangle2D) o;
        return Float.compare(that.width, width) == 0 &&
                Float.compare(that.heigth, heigth) == 0 &&
                Objects.equals(p1, that.p1) &&
                Objects.equals(p2, that.p2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2, width, heigth);
    }

    @Override
    public String toString() {
        return "Rectangle2D{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", width=" + width +
                ", heigth=" + heigth +
                '}';
    }
}
