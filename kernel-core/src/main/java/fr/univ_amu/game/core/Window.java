package fr.univ_amu.game.core;

public interface Window {
    int getWidth();

    int getHeight();

    boolean isClose();

    void swap();

    void make_current();
}
