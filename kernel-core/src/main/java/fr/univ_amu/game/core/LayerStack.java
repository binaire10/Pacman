package fr.univ_amu.game.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LayerStack implements Iterable<Layer> {
    private final List<Layer> stack = new ArrayList<>();
    private int layerCount = 0;

    public LayerStack() {
    }

    public void pushLayer(Layer layer) {
        stack.add(layerCount++, layer);
        layer.onAttach();
    }

    public void pushLayer(Layer... layers) {
        stack.addAll(layerCount, List.of(layers));
        layerCount += layers.length;
        for (Layer layer : layers)
            layer.onAttach();
    }

    public void pushOverlay(Layer overlay) {
        stack.add(overlay);
        overlay.onAttach();
    }

    public void pushOverlay(Layer... overlays) {
        stack.addAll(List.of(overlays));
        for (Layer overlay : overlays)
            overlay.onAttach();
    }

    public void popOverlay(Layer overlay) {
        int index = stack.lastIndexOf(overlay);
        if (index < layerCount || index == -1)
            return;
        stack.remove(index);
        overlay.onDetach();
    }

    public void popLayer(Layer layer) {
        int index = stack.indexOf(layer);
        if (index >= layerCount || index == -1)
            return;
        --layerCount;
        stack.remove(index);
        layer.onDetach();
    }

    public void clear() {
        for (int i = stack.size(); i > 0; ) {
            stack.get(--i).onDetach();
        }
        stack.clear();
    }

    @Override
    public Iterator<Layer> iterator() {
        return stack.iterator();
    }

}
