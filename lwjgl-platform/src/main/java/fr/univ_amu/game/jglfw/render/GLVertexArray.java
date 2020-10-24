package fr.univ_amu.game.jglfw.render;

import fr.univ_amu.game.jglfw.LWJGLPlatform;
import fr.univ_amu.game.render.BufferLayout;
import fr.univ_amu.game.render.IndexBuffer;
import fr.univ_amu.game.render.VertexArray;
import fr.univ_amu.game.render.VertexBuffer;

import static org.lwjgl.opengl.GL30.*;

public class GLVertexArray implements VertexArray {
    private final int vertexArray;

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
    }

    @Override
    public void bind() {
        glBindVertexArray(vertexArray);
    }
}
