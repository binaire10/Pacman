package fr.univ_amu.game.event.mouse;

import fr.univ_amu.game.core.MouseCode;

public final class MouseButtonPressedEvent extends MouseButtonEvent {
    public MouseButtonPressedEvent(MouseCode button) {
        super(button);
    }

    @Override
    public String toString() {
        return "MouseButtonPressedEvent{" +
                "button=" + button +
                '}';
    }
}
