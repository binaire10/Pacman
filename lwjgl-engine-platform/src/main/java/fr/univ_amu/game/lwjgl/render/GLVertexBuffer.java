package fr.univ_amu.game.lwjgl.render;

import fr.univ_amu.game.render.VertexBuffer;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

public class GLVertexBuffer implements VertexBuffer {
    private final int id;

    public GLVertexBuffer(float[] data) {
        id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
    }

    public GLVertexBuffer(int capacity) {
        id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferData(GL_ARRAY_BUFFER, capacity, GL_DYNAMIC_DRAW);
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    @Override
    public void write(int[] data) {
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferSubData(GL_ARRAY_BUFFER, 0, data);
    }

    @Override
    public void write(float[] data) {
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferSubData(GL_ARRAY_BUFFER, 0, data);
    }

    @Override
    public void write(IntBuffer data) {
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferSubData(GL_ARRAY_BUFFER, 0, data);
    }

    @Override
    public void write(FloatBuffer data) {
        glBindBuffer(GL_ARRAY_BUFFER, id);
        glBufferSubData(GL_ARRAY_BUFFER, 0, data);
    }

    @Override
    public void close() throws IOException {
        System.out.println("free vertex buffer");
        glDeleteBuffers(id);
    }
}
