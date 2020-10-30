package fr.univ_amu.game.core;

public class Main {
    public static void main(String[] arg) {
        Platform.initialise();
        double start = System.currentTimeMillis();
        while (Platform.isRunning()) {
            final double current = System.currentTimeMillis();
            final double delta = start - current;
            start = current;
            Platform.getLayerStack().iterator().forEachRemaining(layer -> layer.onUpdate(delta));
            Platform.processEvent();
        }
        Platform.clear();
    }
}
