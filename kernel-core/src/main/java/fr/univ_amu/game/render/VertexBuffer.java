package fr.univ_amu.game.render;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface VertexBuffer extends Bindable {
    void write(IntBuffer data);

    void write(FloatBuffer data);
}
