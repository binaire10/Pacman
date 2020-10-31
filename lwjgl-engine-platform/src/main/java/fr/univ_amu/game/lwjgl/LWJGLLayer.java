package fr.univ_amu.game.lwjgl;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.loader.HardwareLayer;
import fr.univ_amu.game.event.Event;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.*;

@HardwareLayer
public final class LWJGLLayer implements Layer {
    public static final LWJGLLayer LAYER = new LWJGLLayer();

    @Override
    public void onAttach() {
        GLFWWindow mainWindow = new GLFWWindow("", 1, 1);
        mainWindow.make_current();
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        ((LWJGLPlatform) Platform.getGraphicPlatform()).mainWindow = mainWindow;
    }

    @Override
    public void onUpdate(double timestep) {
    }

    @Override
    public void onEvent(Event e) {
    }

    @Override
    public void afterUpdate() {
        glfwPollEvents();
    }

    @Override
    public void onDetach() {
        glfwTerminate();
    }
}
