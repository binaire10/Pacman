package fr.univ_amu.game.beans.bind;

import fr.univ_amu.game.beans.ChangeValueListener;
import fr.univ_amu.game.beans.ObjectProperty;
import fr.univ_amu.game.beans.ObservableValue;
import fr.univ_amu.game.core.Layer;

import java.util.function.Function;

public class BindBidirectionalCustomProperty<F, T> {
    private final Class<? extends Layer> sourceA;
    private final ObjectProperty<T> valueA;
    private final Class<? extends Layer> sourceB;
    private final ObjectProperty<F> valueB;
    private final Function<? super F, ? extends T> convert;
    private final Function<? super T, ? extends F> rconvert;
    private boolean synchronise;

    public BindBidirectionalCustomProperty(Class<? extends Layer> sourceA, ObjectProperty<T> valueA,
                                           Class<? extends Layer> sourceB, ObjectProperty<F> valueB,
                                           Function<? super F, ? extends T> convert,
                                           Function<? super T, ? extends F> rconvert) {
        this.sourceA = sourceA;
        this.valueA = valueA;
        this.sourceB = sourceB;
        this.valueB = valueB;
        this.convert = convert;
        this.rconvert = rconvert;
    }

    public ChangeValueListener<F> getBindA() {
        return new BindA();
    }

    public ChangeValueListener<T> getBindB() {
        return new BindB();
    }

    private class BindA implements ChangeValueListener<F> {
        @Override
        public void update(ObservableValue<? extends F> observable, F from, F to) {
            if (synchronise)
                return;
            synchronise = true;
            valueA.setValue(convert.apply(to));
            synchronise = false;
        }
    }

    private class BindB implements ChangeValueListener<T> {
        @Override
        public void update(ObservableValue<? extends T> observable, T from, T to) {
            if (synchronise)
                return;
            synchronise = true;
            valueB.setValue(rconvert.apply(to));
            synchronise = false;
        }
    }
}
