package fr.univ_amu.game.physics;

import static fr.univ_amu.game.math.VectorUtility.*;

public class Rectangle implements Shape {

    private float[] p1;
    private float[] p2;

    public Rectangle(float[] p1, float[] p2) {
        float minX = Math.min(p1[0],p2[0]);
        float minY = Math.min(p1[1],p2[1]);
        float maxX = Math.max(p1[0],p2[0]);
        float maxY = Math.max(p1[1],p2[1]);
        this.p1 = new float[]{minX, minY};
        this.p2 = new float[]{maxX, maxY};;
    }

    @Override
    public boolean isCollided(Shape anotherShape) {
        if(anotherShape instanceof Rectangle) {
            Rectangle rec2 = (Rectangle) anotherShape;
            return (this.p2[0] >= rec2.p1[0] &&
                    this.p2[1] >= rec2.p1[1] &&
                    rec2.p2[0] >= this.p1[0] &&
                    rec2.p2[1] >= this.p1[1]);
        }
        return false;
    }

    @Override
    public void applyVector(float[] vector) {
        this.p1 = sum(p1,vector);
        this.p2 = sum(p2, vector);
    }
}
