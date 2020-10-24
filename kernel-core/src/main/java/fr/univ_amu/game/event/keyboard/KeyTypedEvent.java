package fr.univ_amu.game.event.keyboard;

import fr.univ_amu.game.event.Event;

public final class KeyTypedEvent extends Event {
    public final int character;

    public KeyTypedEvent(int character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "KeyTypedEvent{" +
                "character=" + character +
                '}';
    }
}
