package fr.univ_amu.game.event.mouse;

import fr.univ_amu.game.core.MouseCode;
import fr.univ_amu.game.event.Event;

public class MouseButtonEvent extends Event {
    public final MouseCode button;

    public MouseButtonEvent(MouseCode button) {
        this.button = button;
    }
}
