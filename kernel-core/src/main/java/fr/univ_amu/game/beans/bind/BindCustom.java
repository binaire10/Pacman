package fr.univ_amu.game.beans.bind;

import fr.univ_amu.game.beans.ChangeValueListener;
import fr.univ_amu.game.beans.ObjectProperty;
import fr.univ_amu.game.beans.ObservableValue;
import fr.univ_amu.game.core.Layer;

import java.util.function.Function;

public class BindCustom<F, T> implements ChangeValueListener<F> {
    private final Class<? extends Layer> source;
    private final Class<? extends Layer> destination;
    private final ObjectProperty<T> value;
    private final Function<? super F, ? extends T> convert;

    public BindCustom(Class<? extends Layer> source, Class<? extends Layer> destination, ObjectProperty<T> value, Function<? super F, ? extends T> convert) {
        this.source = source;
        this.destination = destination;
        this.value = value;
        this.convert = convert;
    }

    @Override
    public void update(ObservableValue<? extends F> observable, F from, F to) {
        value.setValue(convert.apply(to));
    }
}
