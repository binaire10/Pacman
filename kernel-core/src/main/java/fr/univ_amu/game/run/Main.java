package fr.univ_amu.game.run;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Platform;

/**
 * Point de départ de notre programme,lançant les different moteurs necessaire à notre programme
 */
public class Main implements Runnable {
    private double start = System.currentTimeMillis();

    public static void main(String[] arg) {
        Platform.startMainThread(Main::new);
    }

    @Override
    public void run() {
        final double current = System.currentTimeMillis();
        final double delta = (current - start) / 1000;
        start = current;
        Platform.getLayerStack().iterator().forEachRemaining(Layer::beforeUpdate);
        Platform.getLayerStack().iterator().forEachRemaining(layer -> layer.onUpdate(delta));
        Platform.getLayerStack().reverseIterator().forEachRemaining(Layer::afterUpdate);
    }
}
