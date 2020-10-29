package fr.univ_amu.game.event;

import fr.univ_amu.game.core.Window;

public class WindowEvent extends Event {
    private final Window target;

    public WindowEvent(Window target) {
        this.target = target;
    }

    public Window getTarget() {
        return target;
    }
}
