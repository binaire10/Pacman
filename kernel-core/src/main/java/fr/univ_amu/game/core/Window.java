package fr.univ_amu.game.core;

public interface Window {
    int getWidth();

    int getHeight();

    boolean isClose();

    void swap();

    void make_context();

    void show();

    void resize(int width, int height);

    void setTitle(CharSequence title);
}
