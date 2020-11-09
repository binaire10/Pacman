package fr.univ_amu.game.run;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.UpdatableLayer;

public class Main implements Runnable {
    private double start = System.currentTimeMillis();

    public static void main(String[] arg) {
        Platform.startMainThread(new Main());
    }

    @Override
    public void run() {
        final double current = System.currentTimeMillis();
        final double delta = current - start;
        start = current;
        Platform.getLayerStack().iterator().forEachRemaining(UpdatableLayer::beforeUpdate);
        Platform.getLayerStack().iterator().forEachRemaining(layer -> layer.onUpdate(delta));
        Platform.getLayerStack().reverseIterator().forEachRemaining(UpdatableLayer::afterUpdate);
    }
}
