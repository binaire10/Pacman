package fr.univ_amu.game.render;

public interface VertexArray extends Bindable {
    void setVertexBuffer(VertexBuffer vertexBuffer, BufferLayout bufferLayout);

    void setIndexBuffer(IndexBuffer indexBuffer);

    int getComponentCount();
}
