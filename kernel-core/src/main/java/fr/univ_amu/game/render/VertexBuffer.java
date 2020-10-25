package fr.univ_amu.game.render;

import java.io.Closeable;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface VertexBuffer extends Bindable, Closeable {
    void write(int[] data);

    void write(float[] data);

    void write(IntBuffer data);

    void write(FloatBuffer data);
}
