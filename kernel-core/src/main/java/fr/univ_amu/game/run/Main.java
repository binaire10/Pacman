package fr.univ_amu.game.run;

import fr.univ_amu.game.core.CommandExecutor;
import fr.univ_amu.game.core.MainLayer;
import fr.univ_amu.game.core.Platform;

public class Main {
    public static void main(String[] arg) {
        Platform.initialise();
        double start = System.currentTimeMillis();
        CommandExecutor commandExecutor = Platform.getMainCommandExecutor();
        while (Platform.isRunning()) {
            final double current = System.currentTimeMillis();
            final double delta = current - start;
            start = current;
            commandExecutor.compute();
            Platform.getLayerStack().iterator().forEachRemaining(MainLayer::beforeUpdate);
            Platform.getLayerStack().iterator().forEachRemaining(layer -> layer.onUpdate(delta));
            Platform.getLayerStack().reverseIterator().forEachRemaining(MainLayer::afterUpdate);
        }
        Platform.clear();
    }
}
