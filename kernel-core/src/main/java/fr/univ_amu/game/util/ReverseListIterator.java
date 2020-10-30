package fr.univ_amu.game.util;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ReverseListIterator<T> implements Iterator<T> {
    private final ListIterator<T> iterator;

    public ReverseListIterator(List<T> array) {
        iterator = array.listIterator(array.size());
    }

    @Override
    public boolean hasNext() {
        return iterator.hasPrevious();
    }

    @Override
    public T next() {
        return iterator.previous();
    }
}
