package fr.univ_amu.game.lwjgl.render;

import fr.univ_amu.game.render.IndexBuffer;

import static org.lwjgl.opengl.GL33.*;

public final class GLIndexBuffer implements IndexBuffer {
    private final int id;
    private final int count;

    public GLIndexBuffer(int[] data) {
        id = glGenBuffers();
        count = data.length;
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }

    @Override
    public void close() {
        System.out.println("free index buffer");
        glDeleteBuffers(id);
    }
}
