package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;

import static org.lwjgl.opengl.GL33.*;

public class Main {
    public static void main(String[] args) {
        Window window = Platform.create_window("pacman", 720, 400);
        while (!window.isClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            window.swap();
            Platform.processEvent();
        }
    }
}
