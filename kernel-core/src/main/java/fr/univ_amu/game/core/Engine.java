package fr.univ_amu.game.core;

public interface Engine extends Runnable {
    LayerStack<? extends Layer> getStack();

    void initialize();

    void one_step();

    void release();

    void shutdown();

    boolean isStop();

    @Override
    default void run() {
        initialize();
        while (!isStop())
            one_step();
        release();
    }
}
