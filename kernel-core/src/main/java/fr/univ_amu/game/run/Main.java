package fr.univ_amu.game.run;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Platform;

public class Main {
    public static void main(String[] arg) {
        Platform.initialise();
        double start = System.currentTimeMillis();
        while (Platform.isRunning()) {
            final double current = System.currentTimeMillis();
            final double delta = current - start;
            start = current;
            System.out.println(delta);
            Platform.getLayerStack().iterator().forEachRemaining(Layer::beforeUpdate);
            Platform.getLayerStack().iterator().forEachRemaining(layer -> layer.onUpdate(delta));
            Platform.getLayerStack().reverseIterator().forEachRemaining(Layer::afterUpdate);
            Platform.processEvent();
        }
        Platform.clear();
    }
}