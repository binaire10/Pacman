package fr.univ_amu.game.event.application;

import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.WindowEvent;

public final class WindowResizeEvent extends WindowEvent {
    public final int width;
    public final int height;

    public WindowResizeEvent(int width, int height, Window window) {
        super(window);
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "WindowResizeEvent{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
