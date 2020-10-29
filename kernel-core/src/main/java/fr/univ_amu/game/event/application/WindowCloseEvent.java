package fr.univ_amu.game.event.application;

import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.WindowEvent;

public final class WindowCloseEvent extends WindowEvent {
    public WindowCloseEvent(Window target) {
        super(target);
    }

    @Override
    public String toString() {
        return "WindowCloseEvent{}";
    }
}
