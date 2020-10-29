package fr.univ_amu.game.event.mouse;

import fr.univ_amu.game.core.MouseCode;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.WindowEvent;

public class MouseButtonEvent extends WindowEvent {
    public final MouseCode button;

    public MouseButtonEvent(MouseCode button, Window window) {
        super(window);
        this.button = button;
    }
}
