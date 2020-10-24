package fr.univ_amu.game.event.keyboard;

import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.event.Event;

public class KeyEvent extends Event {
    public final KeyCode keyCode;

    public KeyEvent(KeyCode keyCode) {
        this.keyCode = keyCode;
    }
}
