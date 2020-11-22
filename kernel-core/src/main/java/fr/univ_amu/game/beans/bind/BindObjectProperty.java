package fr.univ_amu.game.beans.bind;

import fr.univ_amu.game.beans.ChangeValueListener;
import fr.univ_amu.game.beans.ObjectProperty;
import fr.univ_amu.game.beans.ObservableValue;
import fr.univ_amu.game.core.Layer;

public class BindObjectProperty<T> implements ChangeValueListener<T> {
    private final Class<? extends Layer> source;
    private final Class<? extends Layer> destination;
    private final ObjectProperty<T> value;

    public BindObjectProperty(Class<? extends Layer> source, Class<? extends Layer> destination, ObjectProperty<T> value) {
        this.source = source;
        this.destination = destination;
        this.value = value;
    }

    @Override
    public void update(ObservableValue<? extends T> observable, T from, T to) {
        value.setValue(to);
    }
}
