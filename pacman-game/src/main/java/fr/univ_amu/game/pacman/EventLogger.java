package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.event.Event;

public class EventLogger implements Layer {
    @Override
    public void onAttach() {
        System.out.println("Run Event Logger");
    }

    @Override
    public void onUpdate(double timestep) {

    }

    @Override
    public void onEvent(Event e) {
        System.out.println(e);
    }

    @Override
    public void onDetach() {
        System.out.println("Stop Event Logger");
    }
}
