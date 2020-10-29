package fr.univ_amu.game.event.mouse;

import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.WindowEvent;

public final class MouseScrolledEvent extends WindowEvent {
    public final float xOffset;
    public final float yOffset;

    public MouseScrolledEvent(float xOffset, float yOffset, Window window) {
        super(window);
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
