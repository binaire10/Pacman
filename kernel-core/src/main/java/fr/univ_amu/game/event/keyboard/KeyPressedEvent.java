package fr.univ_amu.game.event.keyboard;

import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Window;

public final class KeyPressedEvent extends KeyEvent {
    private final boolean repeat;

    public KeyPressedEvent(KeyCode keyCode, boolean repeat, Window window) {
        super(keyCode, window);
        this.repeat = repeat;
    }

    @Override
    public String toString() {
        return "KeyPressedEvent{" +
                "repeat=" + repeat +
                ", keyCode=" + keyCode +
                '}';
    }
}
