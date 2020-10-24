package fr.univ_amu.game.event.keyboard;

import fr.univ_amu.game.core.KeyCode;

public final class KeyTypedEvent extends KeyEvent {
    public KeyTypedEvent(KeyCode keyCode) {
        super(keyCode);
    }
}
