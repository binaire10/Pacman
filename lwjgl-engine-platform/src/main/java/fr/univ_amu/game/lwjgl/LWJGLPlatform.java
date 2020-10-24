package fr.univ_amu.game.lwjgl;

import fr.univ_amu.game.core.GraphicPlatform;
import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.lwjgl.render.GLIndexBuffer;
import fr.univ_amu.game.lwjgl.render.GLMaterial;
import fr.univ_amu.game.lwjgl.render.GLVertexArray;
import fr.univ_amu.game.lwjgl.render.GLVertexBuffer;
import fr.univ_amu.game.render.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public final class LWJGLPlatform implements GraphicPlatform {
    private boolean initialize = false;

    public LWJGLPlatform() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
    }

    public static int toOpenGL(ShaderDataType shaderDataType) {
        return switch (shaderDataType) {
            case Int, Int2, Int3, Int4 -> GL30.GL_INT;
            case Float, Float2, Float3, Float4, Float2x2, Float3x3, Float4x4 -> GL30.GL_FLOAT;
            case Bool -> GL30.GL_BOOL;
        };
    }

    public static KeyCode keyFromGLFW(int code) {
        return switch (code) {
            case GLFW_KEY_SPACE -> KeyCode.Space;
            case GLFW_KEY_APOSTROPHE -> KeyCode.Apostrophe;
            case GLFW_KEY_COMMA -> KeyCode.Comma;
            case GLFW_KEY_MINUS -> KeyCode.Minus;
            case GLFW_KEY_PERIOD -> KeyCode.Period;
            case GLFW_KEY_SLASH -> KeyCode.Slash;

            case GLFW_KEY_0 -> KeyCode.D0;
            case GLFW_KEY_1 -> KeyCode.D1;
            case GLFW_KEY_2 -> KeyCode.D2;
            case GLFW_KEY_3 -> KeyCode.D3;
            case GLFW_KEY_4 -> KeyCode.D4;
            case GLFW_KEY_5 -> KeyCode.D5;
            case GLFW_KEY_6 -> KeyCode.D6;
            case GLFW_KEY_7 -> KeyCode.D7;
            case GLFW_KEY_8 -> KeyCode.D8;
            case GLFW_KEY_9 -> KeyCode.D9;

            case GLFW_KEY_SEMICOLON -> KeyCode.Semicolon;
            case GLFW_KEY_EQUAL -> KeyCode.Equal;

            case GLFW_KEY_A -> KeyCode.A;
            case GLFW_KEY_B -> KeyCode.B;
            case GLFW_KEY_C -> KeyCode.C;
            case GLFW_KEY_D -> KeyCode.D;
            case GLFW_KEY_E -> KeyCode.E;
            case GLFW_KEY_F -> KeyCode.F;
            case GLFW_KEY_G -> KeyCode.G;
            case GLFW_KEY_H -> KeyCode.H;
            case GLFW_KEY_I -> KeyCode.I;
            case GLFW_KEY_J -> KeyCode.J;
            case GLFW_KEY_K -> KeyCode.K;
            case GLFW_KEY_L -> KeyCode.L;
            case GLFW_KEY_M -> KeyCode.M;
            case GLFW_KEY_N -> KeyCode.N;
            case GLFW_KEY_O -> KeyCode.O;
            case GLFW_KEY_P -> KeyCode.P;
            case GLFW_KEY_Q -> KeyCode.Q;
            case GLFW_KEY_R -> KeyCode.R;
            case GLFW_KEY_S -> KeyCode.S;
            case GLFW_KEY_T -> KeyCode.T;
            case GLFW_KEY_U -> KeyCode.U;
            case GLFW_KEY_V -> KeyCode.V;
            case GLFW_KEY_W -> KeyCode.W;
            case GLFW_KEY_X -> KeyCode.X;
            case GLFW_KEY_Y -> KeyCode.Y;
            case GLFW_KEY_Z -> KeyCode.Z;

            case GLFW_KEY_LEFT_BRACKET -> KeyCode.LeftBracket;
            case GLFW_KEY_BACKSLASH -> KeyCode.Backslash;
            case GLFW_KEY_RIGHT_BRACKET -> KeyCode.RightBracket;
            case GLFW_KEY_GRAVE_ACCENT -> KeyCode.GraveAccent;

            case GLFW_KEY_WORLD_1 -> KeyCode.World1;
            case GLFW_KEY_WORLD_2 -> KeyCode.World2;

            case GLFW_KEY_ESCAPE -> KeyCode.Escape;
            case GLFW_KEY_ENTER -> KeyCode.Enter;
            case GLFW_KEY_TAB -> KeyCode.Tab;
            case GLFW_KEY_BACKSPACE -> KeyCode.Backspace;
            case GLFW_KEY_INSERT -> KeyCode.Insert;
            case GLFW_KEY_DELETE -> KeyCode.Delete;
            case GLFW_KEY_RIGHT -> KeyCode.Right;
            case GLFW_KEY_LEFT -> KeyCode.Left;
            case GLFW_KEY_DOWN -> KeyCode.Down;
            case GLFW_KEY_UP -> KeyCode.Up;
            case GLFW_KEY_PAGE_UP -> KeyCode.PageUp;
            case GLFW_KEY_PAGE_DOWN -> KeyCode.PageDown;
            case GLFW_KEY_HOME -> KeyCode.Home;
            case GLFW_KEY_END -> KeyCode.End;
            case GLFW_KEY_CAPS_LOCK -> KeyCode.CapsLock;
            case GLFW_KEY_SCROLL_LOCK -> KeyCode.ScrollLock;
            case GLFW_KEY_NUM_LOCK -> KeyCode.NumLock;
            case GLFW_KEY_PRINT_SCREEN -> KeyCode.PrintScreen;
            case GLFW_KEY_PAUSE -> KeyCode.Pause;
            case GLFW_KEY_F1 -> KeyCode.F1;
            case GLFW_KEY_F2 -> KeyCode.F2;
            case GLFW_KEY_F3 -> KeyCode.F3;
            case GLFW_KEY_F4 -> KeyCode.F4;
            case GLFW_KEY_F5 -> KeyCode.F5;
            case GLFW_KEY_F6 -> KeyCode.F6;
            case GLFW_KEY_F7 -> KeyCode.F7;
            case GLFW_KEY_F8 -> KeyCode.F8;
            case GLFW_KEY_F9 -> KeyCode.F9;
            case GLFW_KEY_F10 -> KeyCode.F10;
            case GLFW_KEY_F11 -> KeyCode.F11;
            case GLFW_KEY_F12 -> KeyCode.F12;
            case GLFW_KEY_F13 -> KeyCode.F13;
            case GLFW_KEY_F14 -> KeyCode.F14;
            case GLFW_KEY_F15 -> KeyCode.F15;
            case GLFW_KEY_F16 -> KeyCode.F16;
            case GLFW_KEY_F17 -> KeyCode.F17;
            case GLFW_KEY_F18 -> KeyCode.F18;
            case GLFW_KEY_F19 -> KeyCode.F19;
            case GLFW_KEY_F20 -> KeyCode.F20;
            case GLFW_KEY_F21 -> KeyCode.F21;
            case GLFW_KEY_F22 -> KeyCode.F22;
            case GLFW_KEY_F23 -> KeyCode.F23;
            case GLFW_KEY_F24 -> KeyCode.F24;
            case GLFW_KEY_F25 -> KeyCode.F25;

            case GLFW_KEY_KP_0 -> KeyCode.KP0;
            case GLFW_KEY_KP_1 -> KeyCode.KP1;
            case GLFW_KEY_KP_2 -> KeyCode.KP2;
            case GLFW_KEY_KP_3 -> KeyCode.KP3;
            case GLFW_KEY_KP_4 -> KeyCode.KP4;
            case GLFW_KEY_KP_5 -> KeyCode.KP5;
            case GLFW_KEY_KP_6 -> KeyCode.KP6;
            case GLFW_KEY_KP_7 -> KeyCode.KP7;
            case GLFW_KEY_KP_8 -> KeyCode.KP8;
            case GLFW_KEY_KP_9 -> KeyCode.KP9;
            case GLFW_KEY_KP_DECIMAL -> KeyCode.KPDecimal;
            case GLFW_KEY_KP_DIVIDE -> KeyCode.KPDivide;
            case GLFW_KEY_KP_MULTIPLY -> KeyCode.KPMultiply;
            case GLFW_KEY_KP_SUBTRACT -> KeyCode.KPSubtract;
            case GLFW_KEY_KP_ADD -> KeyCode.KPAdd;
            case GLFW_KEY_KP_ENTER -> KeyCode.KPEnter;
            case GLFW_KEY_KP_EQUAL -> KeyCode.KPEqual;

            case GLFW_KEY_LEFT_SHIFT -> KeyCode.LeftShift;
            case GLFW_KEY_LEFT_CONTROL -> KeyCode.LeftControl;
            case GLFW_KEY_LEFT_ALT -> KeyCode.LeftAlt;
            case GLFW_KEY_LEFT_SUPER -> KeyCode.LeftSuper;
            case GLFW_KEY_RIGHT_SHIFT -> KeyCode.RightShift;
            case GLFW_KEY_RIGHT_CONTROL -> KeyCode.RightControl;
            case GLFW_KEY_RIGHT_ALT -> KeyCode.RightAlt;
            case GLFW_KEY_RIGHT_SUPER -> KeyCode.RightSuper;
            case GLFW_KEY_MENU -> KeyCode.Menu;

            default -> throw new IllegalArgumentException("Unmapped character");
        };
    }

    public static void dispatch(Event event) {

    }

    @Override
    public Window create_window(String title, int width, int height) {
        Window window = new GLFWWindow(title, width, height);
        if (!initialize) {
            GL.createCapabilities();
            initialize = true;
        }
        return window;
    }

    @Override
    public IndexBuffer make_index(int[] index) {
        return new GLIndexBuffer(IntBuffer.wrap(index));
    }

    @Override
    public VertexBuffer make_buffer(float[] data) {
        return new GLVertexBuffer(FloatBuffer.wrap(data));
    }

    @Override
    public VertexBuffer make_buffer(int capacity) {
        return new GLVertexBuffer(capacity);
    }

    @Override
    public VertexArray create_vertexArray() {
        return new GLVertexArray();
    }

    @Override
    public Material create_material(Map<ShaderType, String> shaders) {
        return new GLMaterial(shaders);
    }
}
