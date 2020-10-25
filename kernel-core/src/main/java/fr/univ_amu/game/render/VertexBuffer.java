package fr.univ_amu.game.render;

import java.io.Closeable;

public interface VertexBuffer extends Bindable, Closeable {
    void write(int[] data);

    void write(float[] data);
}
