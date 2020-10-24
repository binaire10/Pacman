package fr.univ_amu.game.event.keyboard;

import fr.univ_amu.game.core.KeyCode;

public final class KeyPressedEvent extends KeyEvent {
    private final boolean repeat;

    public KeyPressedEvent(KeyCode keyCode, boolean repeat) {
        super(keyCode);
        this.repeat = repeat;
    }
}
