package fr.univ_amu.game.event.application;

import fr.univ_amu.game.event.Event;

public final class WindowResizeEvent extends Event {
    public final int width;
    public final int height;

    public WindowResizeEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
