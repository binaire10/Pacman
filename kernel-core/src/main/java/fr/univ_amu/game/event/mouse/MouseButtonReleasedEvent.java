package fr.univ_amu.game.event.mouse;

import fr.univ_amu.game.core.MouseCode;

public final class MouseButtonReleasedEvent extends MouseButtonEvent {
    public MouseButtonReleasedEvent(MouseCode button) {
        super(button);
    }

    @Override
    public String toString() {
        return "MouseButtonReleasedEvent{" +
                "button=" + button +
                '}';
    }
}
