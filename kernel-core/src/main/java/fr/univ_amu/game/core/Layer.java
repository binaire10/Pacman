package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;

/**
 * Interface nous permettant de marquer nos differentes classe(moteur) maitresse et necessitant un fonctionnement synchronisé partageant des fonction commune
 * s'executant "ensemble"
 */
public interface Layer {
    void onAttach();

    void onDetach();

    default void beforeUpdate() {
    }

    void onUpdate(double timestep);

    default void afterUpdate() {
    }

    void onEvent(Event e);

}
