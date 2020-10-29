package fr.univ_amu.game.event.keyboard;

import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.WindowEvent;

public final class KeyTypedEvent extends WindowEvent {
    public final int character;

    public KeyTypedEvent(int character, Window window) {
        super(window);
        this.character = character;
    }

    @Override
    public String toString() {
        return "KeyTypedEvent{" +
                "character=" + character +
                '}';
    }
}
