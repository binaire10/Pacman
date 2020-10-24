package fr.univ_amu.game.event.keyboard;

import fr.univ_amu.game.core.KeyCode;

public final class KeyReleasedEvent extends KeyEvent {
    public KeyReleasedEvent(KeyCode keyCode) {
        super(keyCode);
    }
}
