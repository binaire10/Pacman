package fr.univ_amu.game.lwjgl.render;

import fr.univ_amu.game.lwjgl.LWJGLPlatform;
import fr.univ_amu.game.render.BufferLayout;
import fr.univ_amu.game.render.IndexBuffer;
import fr.univ_amu.game.render.VertexArray;
import fr.univ_amu.game.render.VertexBuffer;

import java.io.IOException;

import static org.lwjgl.opengl.GL33.*;

public class GLVertexArray implements VertexArray {
    private final int vertexArray;
    private int componentCount = 0;

    public GLVertexArray() {
        this.vertexArray = glGenVertexArrays();
    }

    @Override
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

    @Override
    public void setIndexBuffer(IndexBuffer indexBuffer) {
        glBindVertexArray(vertexArray);
        indexBuffer.bind();
        componentCount = indexBuffer.count();
    }

    @Override
    public int getComponentCount() {
        return componentCount;
    }

    @Override
    public void bind() {
        glBindVertexArray(vertexArray);
    }

    @Override
    public void close() throws IOException {
        System.out.println("free vertex array");
        glDeleteVertexArrays(vertexArray);
    }
}
