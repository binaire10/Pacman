package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;

public interface UpdatableLayer extends Layer {
    default void beforeUpdate() {
    }

    void onUpdate(double timestep);

    default void afterUpdate() {
    }

    void onEvent(Event e);

}
