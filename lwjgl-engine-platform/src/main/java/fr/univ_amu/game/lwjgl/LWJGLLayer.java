package fr.univ_amu.game.lwjgl;

import fr.univ_amu.game.core.MainLayer;
import fr.univ_amu.game.core.loader.HardwareLayer;
import fr.univ_amu.game.event.Event;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;

@HardwareLayer
public final class LWJGLLayer implements MainLayer {
    @Override
    public void onAttach() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
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
