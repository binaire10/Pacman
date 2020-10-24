package fr.univ_amu.game.event.mouse;

import fr.univ_amu.game.event.Event;

public final class MouseScrolledEvent extends Event {
    public final float xOffset;
    public final float yOffset;

    public MouseScrolledEvent(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    @Override
    public String toString() {
        return "MouseScrolledEvent{" +
                "xOffset=" + xOffset +
                ", yOffset=" + yOffset +
                '}';
    }
}
