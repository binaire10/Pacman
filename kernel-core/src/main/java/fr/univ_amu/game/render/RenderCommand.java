package fr.univ_amu.game.render;

public interface RenderCommand {
    void setViewport(int x, int y, int width, int height);

    void drawElements(VertexArray vertexArray, int count);

    void setClear(float r, float g, float b, float a);

    void clear();
}
