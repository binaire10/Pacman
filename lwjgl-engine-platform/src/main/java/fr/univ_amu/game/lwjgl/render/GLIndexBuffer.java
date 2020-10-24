package fr.univ_amu.game.lwjgl.render;

import fr.univ_amu.game.render.IndexBuffer;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL30.*;

public class GLIndexBuffer implements IndexBuffer {
    private final int id;
    private final int length;

    public GLIndexBuffer(IntBuffer data) {
        id = glGenBuffers();
        length = data.remaining();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }
}
