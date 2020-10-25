package fr.univ_amu.game.render;

import java.io.Closeable;

public interface VertexArray extends Bindable, Closeable {
    void setVertexBuffer(VertexBuffer vertexBuffer, BufferLayout bufferLayout);

    void setIndexBuffer(IndexBuffer indexBuffer);

    int getComponentCount();
}
