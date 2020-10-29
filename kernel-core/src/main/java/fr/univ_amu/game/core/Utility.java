package fr.univ_amu.game.core;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Utility {
    public static <T> Stream<T> iteratorToStream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
    }

    public static <T> Stream<T> iteratorToStream(Iterator<T> iterator, long size) {
        return StreamSupport.stream(Spliterators.spliterator(iterator, size, 0), false);
    }
}
