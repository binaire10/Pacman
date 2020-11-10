package fr.univ_amu.game.lwjgl.render;

import static org.lwjgl.opengl.GL33.*;

public final class IndexBuffer implements Bindable {
    private final int id;
    private final int count;

    public IndexBuffer(int[] data) {
        id = glGenBuffers();
        count = data.length;
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
    }

    public int count() {
        return count;
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }

    public void close() {
        System.out.println("free index buffer");
        glDeleteBuffers(id);
    }
}
