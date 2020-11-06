package fr.univ_amu.game.core;

public interface Engine extends Runnable {
    LayerStack<? extends Layer> getStack();

    void shutdown();
}
