package fr.univ_amu.game.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LayerStack implements Iterable<Layer> {
    private final List<Layer> stack = new ArrayList<>();
    private int layerCount = 0;

    public LayerStack() {
    }

    public void pushLayers(Layer layer) {
        stack.add(layerCount++, layer);
    }

    public void pushLayers(Layer... layers) {
        stack.addAll(layerCount, List.of(layers));
        layerCount += layers.length;
    }

    public void pushOverlay(Layer overlay) {
        stack.add(overlay);
    }

    public void pushOverlays(Layer... overlay) {
        stack.addAll(List.of(overlay));
    }

    public void popOverlays(Layer overlay) {
        int index = stack.lastIndexOf(overlay);
        if (index < layerCount || index == -1)
            return;
        stack.remove(index);
    }

    public void popLayer(Layer overlay) {
        int index = stack.indexOf(overlay);
        if (index >= layerCount || index == -1)
            return;
        --layerCount;
        stack.remove(index);
    }

    @Override
    public Iterator<Layer> iterator() {
        return stack.iterator();
    }
}
