package fr.univ_amu.game.run;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Platform;

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
        Platform.getLayerStack().iterator().forEachRemaining(Layer::beforeUpdate);
        Platform.getLayerStack().iterator().forEachRemaining(layer -> layer.onUpdate(delta));
        Platform.getLayerStack().reverseIterator().forEachRemaining(Layer::afterUpdate);
    }
}
