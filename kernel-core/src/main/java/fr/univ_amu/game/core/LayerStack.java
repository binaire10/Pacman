package fr.univ_amu.game.core;

import fr.univ_amu.game.util.ReverseListIterator;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LayerStack<T extends Layer> implements Iterable<T> {
    private final List<T> stack = new CopyOnWriteArrayList<>();
    private int layerCount = 0;

    public void pushLayer(T layer) {
        stack.add(layerCount++, layer);
        layer.onAttach();
    }

    public void pushLayer(T... layers) {
        stack.addAll(layerCount, List.of(layers));
        layerCount += layers.length;
        for (Layer layer : layers)
            layer.onAttach();
    }

    public void pushOverlay(T overlay) {
        stack.add(overlay);
        overlay.onAttach();
    }

    public void pushOverlay(T... overlays) {
        stack.addAll(List.of(overlays));
        for (Layer overlay : overlays)
            overlay.onAttach();
    }

    public void popOverlay(T overlay) {
        int index = stack.lastIndexOf(overlay);
        if (index < layerCount || index == -1)
            return;
        stack.remove(index);
        overlay.onDetach();
    }

    public void popLayer(T layer) {
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
    public Iterator<T> iterator() {
        return stack.iterator();
    }

    public Iterator<T> reverseIterator() {
        return new ReverseListIterator<>(stack);
    }
}
