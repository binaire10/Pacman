package fr.univ_amu.game.event.mouse;

import fr.univ_amu.game.event.Event;

public final class MouseMovedEvent extends Event {
    public final float mouseX;
    public final float mouseY;

    public MouseMovedEvent(float mouseX, float mouseY) {
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
