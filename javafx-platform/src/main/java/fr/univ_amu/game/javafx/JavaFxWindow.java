package fr.univ_amu.game.javafx;

import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.event.keyboard.KeyPressedEvent;
import fr.univ_amu.game.event.keyboard.KeyReleasedEvent;
import fr.univ_amu.game.javafx.render.JavaFXRenderCommand;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFxWindow implements Window {
    private final Stage stage;
    private final Scene scene;
    Group canvas;

    public JavaFxWindow(String title, int width, int height) {
        canvas = new Group();
        stage = new Stage();
        scene = new Scene(canvas, width, height, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setOnCloseRequest((e) -> Platform.dispatch(new WindowCloseEvent(this)));
        scene.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent k) -> Platform.dispatch(new KeyReleasedEvent(keyboardFromJavaFX(k.getCode()), this)));
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent k) -> Platform.dispatch(new KeyPressedEvent(keyboardFromJavaFX(k.getCode()), this)));
    }

    public static KeyCode keyboardFromJavaFX(javafx.scene.input.KeyCode code) {
        return switch (code) {
            case SPACE -> KeyCode.Space;
//            case GLFW_KEY_APOSTROPHE -> KeyCode.Apostrophe;
            case COMMA -> KeyCode.Comma;
            case MINUS -> KeyCode.Minus;
            case PERIOD -> KeyCode.Period;
            case SLASH -> KeyCode.Slash;

            case SOFTKEY_0 -> KeyCode.D0;
            case SOFTKEY_1 -> KeyCode.D1;
            case SOFTKEY_2 -> KeyCode.D2;
            case SOFTKEY_3 -> KeyCode.D3;
            case SOFTKEY_4 -> KeyCode.D4;
            case SOFTKEY_5 -> KeyCode.D5;
            case SOFTKEY_6 -> KeyCode.D6;
            case SOFTKEY_7 -> KeyCode.D7;
            case SOFTKEY_8 -> KeyCode.D8;
            case SOFTKEY_9 -> KeyCode.D9;

            case SEMICOLON -> KeyCode.Semicolon;
            case EQUALS -> KeyCode.Equal;

            case A -> KeyCode.A;
            case B -> KeyCode.B;
            case C -> KeyCode.C;
            case D -> KeyCode.D;
            case E -> KeyCode.E;
            case F -> KeyCode.F;
            case G -> KeyCode.G;
            case H -> KeyCode.H;
            case I -> KeyCode.I;
            case J -> KeyCode.J;
            case K -> KeyCode.K;
            case L -> KeyCode.L;
            case M -> KeyCode.M;
            case N -> KeyCode.N;
            case O -> KeyCode.O;
            case P -> KeyCode.P;
            case Q -> KeyCode.Q;
            case R -> KeyCode.R;
            case S -> KeyCode.S;
            case T -> KeyCode.T;
            case U -> KeyCode.U;
            case V -> KeyCode.V;
            case W -> KeyCode.W;
            case X -> KeyCode.X;
            case Y -> KeyCode.Y;
            case Z -> KeyCode.Z;

            case BRACELEFT -> KeyCode.LeftBracket;
            case BACK_SLASH -> KeyCode.Backslash;
            case BRACERIGHT -> KeyCode.RightBracket;
//            case GLFW_KEY_GRAVE_ACCENT -> KeyCode.GraveAccent;

            case WINDOWS -> KeyCode.World1;
//            case WINDOWS -> KeyCode.World2;

            case ESCAPE -> KeyCode.Escape;
            case ENTER -> KeyCode.Enter;
            case TAB -> KeyCode.Tab;
            case BACK_SPACE -> KeyCode.Backspace;
            case INSERT -> KeyCode.Insert;
            case DELETE -> KeyCode.Delete;
            case RIGHT -> KeyCode.Right;
            case LEFT -> KeyCode.Left;
            case DOWN -> KeyCode.Down;
            case UP -> KeyCode.Up;
            case PAGE_UP -> KeyCode.PageUp;
            case PAGE_DOWN -> KeyCode.PageDown;
            case HOME -> KeyCode.Home;
            case END -> KeyCode.End;
            case CAPS -> KeyCode.CapsLock;
            case SCROLL_LOCK -> KeyCode.ScrollLock;
            case NUM_LOCK -> KeyCode.NumLock;
            case PRINTSCREEN -> KeyCode.PrintScreen;
            case PAUSE -> KeyCode.Pause;
            case F1 -> KeyCode.F1;
            case F2 -> KeyCode.F2;
            case F3 -> KeyCode.F3;
            case F4 -> KeyCode.F4;
            case F5 -> KeyCode.F5;
            case F6 -> KeyCode.F6;
            case F7 -> KeyCode.F7;
            case F8 -> KeyCode.F8;
            case F9 -> KeyCode.F9;
            case F10 -> KeyCode.F10;
            case F11 -> KeyCode.F11;
            case F12 -> KeyCode.F12;
            case F13 -> KeyCode.F13;
            case F14 -> KeyCode.F14;
            case F15 -> KeyCode.F15;
            case F16 -> KeyCode.F16;
            case F17 -> KeyCode.F17;
            case F18 -> KeyCode.F18;
            case F19 -> KeyCode.F19;
            case F20 -> KeyCode.F20;
            case F21 -> KeyCode.F21;
            case F22 -> KeyCode.F22;
            case F23 -> KeyCode.F23;
            case F24 -> KeyCode.F24;
//            case F25 -> KeyCode.F25;

            case NUMPAD0 -> KeyCode.KP0;
            case NUMPAD1 -> KeyCode.KP1;
            case NUMPAD2 -> KeyCode.KP2;
            case NUMPAD3 -> KeyCode.KP3;
            case NUMPAD4 -> KeyCode.KP4;
            case NUMPAD5 -> KeyCode.KP5;
            case NUMPAD6 -> KeyCode.KP6;
            case NUMPAD7 -> KeyCode.KP7;
            case NUMPAD8 -> KeyCode.KP8;
            case NUMPAD9 -> KeyCode.KP9;
            case DECIMAL -> KeyCode.KPDecimal;
            case DIVIDE -> KeyCode.KPDivide;
            case MULTIPLY -> KeyCode.KPMultiply;
            case SUBTRACT -> KeyCode.KPSubtract;
            case ADD -> KeyCode.KPAdd;
//            case ENTER -> KeyCode.KPEnter;
//            case EQUAL -> KeyCode.KPEqual;

            case SHIFT -> KeyCode.LeftShift;
            case CONTROL -> KeyCode.LeftControl;
            case ALT -> KeyCode.LeftAlt;
//            case GLFW_KEY_LEFT_SUPER -> KeyCode.LeftSuper;
//            case GLFW_KEY_RIGHT_SHIFT -> KeyCode.RightShift;
//            case GLFW_KEY_RIGHT_CONTROL -> KeyCode.RightControl;
//            case GLFW_KEY_RIGHT_ALT -> KeyCode.RightAlt;
//            case GLFW_KEY_RIGHT_SUPER -> KeyCode.RightSuper;
            case CONTEXT_MENU -> KeyCode.Menu;

            default -> throw new IllegalArgumentException("Unmapped keyboard code");
        };
    }

    @Override
    public int getWidth() {
        return (int) scene.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) scene.getHeight();
    }

    @Override
    public boolean isClose() {
        return !stage.isShowing();
    }

    @Override
    public void swap() {
    }

    @Override
    public void make_context() {
        ((JavaFXRenderCommand) Platform.getRenderCommand()).setFxWindow(this);
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void resize(int width, int height) {
        canvas.resize(width, height);
    }

    public Group getCanvas() {
        return canvas;
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void setTitle(CharSequence title) {
        stage.setTitle(title.toString());
    }
}
