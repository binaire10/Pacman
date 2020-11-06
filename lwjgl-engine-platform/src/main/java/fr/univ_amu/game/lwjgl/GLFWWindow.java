package fr.univ_amu.game.lwjgl;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.event.application.WindowResizeEvent;
import fr.univ_amu.game.event.keyboard.KeyPressedEvent;
import fr.univ_amu.game.event.keyboard.KeyReleasedEvent;
import fr.univ_amu.game.event.keyboard.KeyTypedEvent;
import fr.univ_amu.game.event.mouse.MouseButtonPressedEvent;
import fr.univ_amu.game.event.mouse.MouseButtonReleasedEvent;
import fr.univ_amu.game.event.mouse.MouseMovedEvent;
import fr.univ_amu.game.event.mouse.MouseScrolledEvent;
import org.lwjgl.opengl.GL;

import java.util.concurrent.FutureTask;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class GLFWWindow implements Window {
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
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        winID = glfwCreateWindow(width, height, title, NULL, NULL);
        if (winID == NULL)
            throw new RuntimeException("Failed to create the GLFW window");
        glfwSetWindowSizeCallback(winID, this::handleResizeEvent);
        glfwSetCharCallback(winID, this::handleTypedEvent);
        glfwSetKeyCallback(winID, this::handleKeyEvent);
        glfwSetWindowCloseCallback(winID, this::handleClose);
        glfwSetMouseButtonCallback(winID, this::handleMouseButtonEvent);
        glfwSetScrollCallback(winID, this::handleScrollEvent);
        glfwSetCursorPosCallback(winID, this::handleCursorPosEvent);
    }

    private void handleTypedEvent(long wid, int key) {
        Platform.dispatch(new KeyTypedEvent(key, this));
    }

    private void handleKeyEvent(long wid, int key, int scancode, int action, int mods) {
        switch (action) {
            case GLFW_PRESS -> Platform.dispatch(new KeyPressedEvent(LWJGLPlatform.keyboardFromGLFW(key), false, this));
            case GLFW_RELEASE -> Platform.dispatch(new KeyReleasedEvent(LWJGLPlatform.keyboardFromGLFW(key), this));
            case GLFW_REPEAT -> Platform.dispatch(new KeyPressedEvent(LWJGLPlatform.keyboardFromGLFW(key), true, this));
        }
    }

    private void handleMouseButtonEvent(long wid, int button, int action, int mods) {
        switch (action) {
            case GLFW_PRESS -> Platform.dispatch(new MouseButtonPressedEvent(LWJGLPlatform.mouseFromGLFW(button), this));
            case GLFW_RELEASE -> Platform.dispatch(new MouseButtonReleasedEvent(LWJGLPlatform.mouseFromGLFW(button), this));
        }
    }

    private void handleScrollEvent(long wid, double xOffset, double yOffset) {
        Platform.dispatch(new MouseScrolledEvent((float) xOffset, (float) yOffset, this));
    }

    private void handleCursorPosEvent(long wid, double xPos, double yPos) {
        Platform.dispatch(new MouseMovedEvent((float) xPos, (float) yPos, this));
    }

    private void handleClose(long wid) {
        Platform.dispatch(new WindowCloseEvent(this));
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

    @Override
    public void make_context() {
        glfwMakeContextCurrent(winID);
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void show() {
        Platform.getMainCommandExecutor().add(new FutureTask<>(() -> {
            glfwShowWindow(winID);
            return null;
        }));
    }

    @Override
    public void resize(int width, int height) {
        glfwSetWindowSize(winID, width, height);
    }

    @Override
    public void setTitle(CharSequence title) {
        glfwSetWindowTitle(winID, title);
    }

    private void handleResizeEvent(long wid, int w, int h) {
        this.width = w;
        this.height = h;
        Platform.dispatch(new WindowResizeEvent(w, h, this));
    }
}
