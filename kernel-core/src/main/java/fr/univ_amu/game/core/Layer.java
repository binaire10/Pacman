package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;

public interface Layer {
    void onAttach();

    void onUpdate(double timestep);

    void onEvent(Event e);

    void onDetach();
}
