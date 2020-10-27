package fr.univ_amu.game.render;

import java.io.Closeable;

public interface IndexBuffer extends Bindable, Closeable {
    int count();
}
