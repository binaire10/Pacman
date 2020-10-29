package fr.univ_amu.game.event.mouse;

import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.WindowEvent;

public final class MouseMovedEvent extends WindowEvent {
    public final float mouseX;
    public final float mouseY;

    public MouseMovedEvent(float mouseX, float mouseY, Window window) {
        super(window);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public String toString() {
        return "MouseMovedEvent{" +
                "mouseX=" + mouseX +
                ", mouseY=" + mouseY +
                '}';
    }
}
