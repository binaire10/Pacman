package fr.univ_amu.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Node<T> implements Iterable<T> {
    private final List<Node<T>> children;
    private T value;

    public Node(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Node(T value, List<Node<T>> sub) {
        this.value = value;
        this.children = sub;
    }

    @SafeVarargs
    public static <R> Node<R> fabric(R value, Node<R>... subNode) {
        List<Node<R>> subNodeArrays = new ArrayList<>();
        Collections.addAll(subNodeArrays, subNode);
        return new Node<>(value, subNodeArrays);
    }

    public void add(Node<T> node) {
        children.add(node);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public Iterator<T> iterator() {
        List<Node<T>> arrayList = new ArrayList<>(1);
        arrayList.add(this);
        return new DepthIterator<>(arrayList.iterator());
    }
}
