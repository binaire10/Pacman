package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;

import java.util.function.Consumer;

public class EventDispatch {
    private final Event event;

    public EventDispatch(Event event) {
        this.event = event;
    }

    public <T extends Event> void dispatch(Class<T> value, Consumer<T> handle) {
        if (value.isInstance(event))
            handle.accept(value.cast(event));
    }
}