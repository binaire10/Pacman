package fr.univ_amu.game.lwjgl.render;

import fr.univ_amu.game.lwjgl.LWJGLPlatform;

import static org.lwjgl.opengl.GL33.*;

public final class VertexArray implements Bindable {
    private final int vertexArray;
    private int componentCount = 0;

    public VertexArray() {
        this.vertexArray = glGenVertexArrays();
    }

    public void setVertexBuffer(VertexBuffer vertexBuffer, BufferLayout bufferLayout) {
        glBindVertexArray(vertexArray);
        vertexBuffer.bind();
        for (var entry : bufferLayout.getEntries()) {
            glEnableVertexAttribArray(entry.getIndex());
            glVertexAttribPointer(
                    entry.getIndex(),
                    entry.type.getComponentCount(),
                    LWJGLPlatform.toOpenGL(entry.type),
                    entry.normalized,
                    bufferLayout.getStride(),
                    entry.getOffset());
        }
    }

    public void setIndexBuffer(IndexBuffer indexBuffer) {
        glBindVertexArray(vertexArray);
        indexBuffer.bind();
        componentCount = indexBuffer.count();
    }

    public int getComponentCount() {
        return componentCount;
    }

    @Override
    public void bind() {
        glBindVertexArray(vertexArray);
    }

    public void close() {
        System.out.println("free vertex array");
        glDeleteVertexArrays(vertexArray);
    }
}
