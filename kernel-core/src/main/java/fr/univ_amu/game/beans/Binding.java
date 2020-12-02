package fr.univ_amu.game.beans;

import fr.univ_amu.game.beans.bind.*;
import fr.univ_amu.game.core.Layer;

import java.util.function.Function;

public final class Binding {
    public static <T> ChangeValueListener<T> bind(
            Class<? extends Layer> emitter, ObservableValue<T> from,
            Class<? extends Layer> receptor, ObjectProperty<? super T> to) {
        to.setValue(from.getValue());
        BindObjectProperty<T> binder = new BindObjectProperty<>(emitter, receptor, to);
        from.addListener(binder);
        return binder;
    }

    public static ChangeValueListener<Float> bind(
            Class<? extends Layer> emitter, ObservableValue<? extends Float> from,
            Class<? extends Layer> receptor, FloatProperty to) {
        to.setValue(from.getValue());
        var binder = new BindFloatProperty(emitter, receptor, to);
        from.addListener(binder);
        return binder;
    }

    public static ChangeValueListener<Integer> bind(
            Class<? extends Layer> emitter, ObservableValue<? extends Integer> from,
            Class<? extends Layer> receptor, IntegerProperty to) {
        to.setValue(from.getValue());
        var binder = new BindIntegerProperty(emitter, receptor, to);
        from.addListener(binder);
        return binder;
    }

    public static <F, T> ChangeValueListener<F> bind(
            Class<? extends Layer> emitter, ObservableValue<F> from,
            Class<? extends Layer> receptor, ObjectProperty<T> to,
            Function<? super F, ? extends T> convert) {
        to.setValue(convert.apply(from.getValue()));
        BindCustom<F, T> listener = new BindCustom<>(emitter, receptor, to, convert);
        from.addListener(listener);
        return listener;
    }

    public static <T> ChangeValueListener<T> bindBidirectional(
            Class<? extends Layer> emitter, ObjectProperty<T> valueA,
            Class<? extends Layer> receptor, ObjectProperty<T> valueB) {
        var binder = new BindBidirectionalObjectProperty<>(emitter, valueA, receptor, valueB);
        valueB.setValue(valueA.getValue());
        valueB.addListener(binder);
        valueA.addListener(binder);
        return binder;
    }

    public static <F, T> void bindBidirectional(
            Class<? extends Layer> emitter, ObjectProperty<F> valueA,
            Class<? extends Layer> receptor, ObjectProperty<T> valueB,
            Function<? super F, ? extends T> convertAB,
            Function<? super T, ? extends F> convertBA) {
        BindBidirectionalCustomProperty<T, F> binder = new BindBidirectionalCustomProperty<T, F>(
                emitter, valueA,
                receptor, valueB,
                convertBA,
                convertAB
        );
        valueB.setValue(convertAB.apply(valueA.getValue()));
        valueB.addListener(binder.getBindA());
        valueA.addListener(binder.getBindB());
    }
}
