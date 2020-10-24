package fr.univ_amu.game.render;

public interface VertexBuffer extends Bindable {
    void write(int[] data);

    void write(float[] data);
}
