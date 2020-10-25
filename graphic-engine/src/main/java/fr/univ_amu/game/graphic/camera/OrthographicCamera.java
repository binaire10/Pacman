package fr.univ_amu.game.graphic.camera;

public class OrthographicCamera implements Camera {
    private final float[] position = {0, 0, 0};
    private float angle = 0;
    private float zoom = 1;

    public OrthographicCamera() {
    }

    public OrthographicCamera(float angle, float zoom) {
        this.angle = angle;
        this.zoom = zoom;
    }

    public OrthographicCamera(float[] position, float angle, float zoom) {
        setPosition(position);
        this.angle = angle;
        this.zoom = zoom;
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
        float cosA = (float) Math.cos(angle);
        float sinA = (float) Math.sin(angle);
        return new float[]{
                cosA * zoom, -sinA, 0, position[0],
                sinA, cosA * zoom, 0, position[1],
                0, 0, zoom, position[2],
                0, 0, 0, 1
        };
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
}
