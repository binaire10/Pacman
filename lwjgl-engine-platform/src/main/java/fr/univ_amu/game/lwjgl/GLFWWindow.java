package fr.univ_amu.game.lwjgl;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.application.WindowResizeEvent;
import fr.univ_amu.game.event.keyboard.KeyPressedEvent;
import fr.univ_amu.game.event.keyboard.KeyReleasedEvent;
import fr.univ_amu.game.event.keyboard.KeyTypedEvent;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GLFWWindow implements Window {
    private final long winID;
    private int width;
    private int height;

    public GLFWWindow(String title, int width, int height) {
        this.width = width;
        this.height = height;
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        winID = glfwCreateWindow(width, height, title, NULL, NULL);
        if (winID == NULL)
            throw new RuntimeException("Failed to create the GLFW window");
        glfwSetWindowSizeCallback(winID, this::handleResizeEvent);
        glfwSetCharCallback(winID, GLFWWindow::handleTypedEvent);
        glfwSetKeyCallback(winID, GLFWWindow::handleKeyEvent);

        glfwMakeContextCurrent(winID);
    }

    private static void handleTypedEvent(long wid, int key) {
        Platform.dispatch(new KeyTypedEvent(key));
    }

    private static void handleKeyEvent(long wid, int key, int scancode, int action, int mods) {
        switch (action) {
            case GLFW_PRESS:
                Platform.dispatch(new KeyPressedEvent(LWJGLPlatform.keyFromGLFW(key), false));
                break;
            case GLFW_RELEASE:
                Platform.dispatch(new KeyReleasedEvent(LWJGLPlatform.keyFromGLFW(key)));
                break;
            case GLFW_REPEAT:
                Platform.dispatch(new KeyPressedEvent(LWJGLPlatform.keyFromGLFW(key), true));
                break;
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isClose() {
        return glfwWindowShouldClose(winID);
    }

    @Override
    public void swap() {
        glfwSwapBuffers(winID);
    }

    private void handleResizeEvent(long wid, int w, int h) {
        this.width = w;
        this.height = h;
        Platform.dispatch(new WindowResizeEvent(w, h));
    }
}
