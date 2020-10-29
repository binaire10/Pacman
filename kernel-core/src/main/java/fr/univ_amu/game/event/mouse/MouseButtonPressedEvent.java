package fr.univ_amu.game.event.mouse;

import fr.univ_amu.game.core.MouseCode;
import fr.univ_amu.game.core.Window;

public final class MouseButtonPressedEvent extends MouseButtonEvent {
    public MouseButtonPressedEvent(MouseCode button, Window window) {
        super(button, window);
    }

    @Override
    public String toString() {
        return "MouseButtonPressedEvent{" +
                "button=" + button +
                '}';
    }
}
