package fr.univ_amu.game.graphic.camera;

import fr.univ_amu.game.math.Mat;

public class OrthographicCamera implements Camera {
    private final float[] position = {0, 0, 0};
    private float angle = 0;
    private float zoom = 1;
    private float ratio;

    public OrthographicCamera(float ratio) {
        this.ratio = ratio;
    }

    public OrthographicCamera(float ratio, float angle, float zoom) {
        this.angle = angle;
        this.zoom = zoom;
        this.ratio = ratio;
    }

    public OrthographicCamera(float ratio, float[] position, float angle, float zoom) {
        setPosition(position);
        this.angle = angle;
        this.zoom = zoom;
        this.ratio = ratio;
    }

    @Override
    public float[] getPosition() {
        return this.position;
    }

    public void setPosition(float[] position) {
        int n = Math.min(this.position.length, position.length);
        for (int i = 0; i < n; i++)
            this.position[i] = position[i];
    }

    public float getZRotation() {
        return angle;
    }

    public void setZRotation(float angle) {
        this.angle = angle;
    }

    @Override
    public float[] getMatrix() {
        float cosA = (float) Math.cos(-angle);
        float sinA = (float) Math.sin(-angle);
        return Mat.dot_product(Mat.ortho(ratio * zoom, -ratio * zoom, -zoom, zoom), new float[]{
                cosA, -sinA, 0, -position[0],
                sinA, cosA, 0, -position[1],
                0, 0, 1, -position[2],
                0, 0, 0, 1
        }, 4);
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
}
