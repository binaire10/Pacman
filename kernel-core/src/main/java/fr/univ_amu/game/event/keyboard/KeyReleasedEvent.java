package fr.univ_amu.game.event.keyboard;

import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Window;

public final class KeyReleasedEvent extends KeyEvent {
    public KeyReleasedEvent(KeyCode keyCode, Window window) {
        super(keyCode, window);
    }

    @Override
    public String toString() {
        return "KeyReleasedEvent{" +
                "keyCode=" + keyCode +
                '}';
    }
}
