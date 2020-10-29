package fr.univ_amu.game.event.keyboard;

import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.WindowEvent;

public class KeyEvent extends WindowEvent {
    public final KeyCode keyCode;

    public KeyEvent(KeyCode keyCode, Window window) {
        super(window);
        this.keyCode = keyCode;
    }
}
