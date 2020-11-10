package fr.univ_amu.game.lwjgl.render;

import fr.univ_amu.game.render.RenderCommand;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class GLRenderCommand implements RenderCommand {

    @Override
    public void setViewport(int x, int y, int width, int height) {
        glViewport(x, y, width, height);
    }

    public static void drawElements(VertexArray vertexArray, int count) {
        glDrawElements(GL_TRIANGLES, count == 0 ? vertexArray.getComponentCount() : count, GL_UNSIGNED_INT, NULL);
    }

    @Override
    public void setClear(float r, float g, float b, float a) {
        glClearColor(r, g, b, a);
    }

    @Override
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
