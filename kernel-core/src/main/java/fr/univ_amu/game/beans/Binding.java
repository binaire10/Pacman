package fr.univ_amu.game.beans;

import fr.univ_amu.game.beans.bind.BindCustom;
import fr.univ_amu.game.beans.bind.BindFloatProperty;
import fr.univ_amu.game.beans.bind.BindIntegerProperty;
import fr.univ_amu.game.beans.bind.BindObjectProperty;
import fr.univ_amu.game.core.Layer;

import java.util.function.Function;

public final class Binding {
    public static <T> void bind(Class<? extends Layer> emitter, ObservableValue<T> from, Class<? extends Layer> receptor, ObjectProperty<? super T> to) {
        from.addListener(new BindObjectProperty<>(emitter, receptor, to));
    }

    public static void bind(Class<? extends Layer> emitter, ObservableValue<? extends Float> from, Class<? extends Layer> receptor, FloatProperty to) {
        from.addListener(new BindFloatProperty(emitter, receptor, to));
    }

    public static void bind(Class<? extends Layer> emitter, ObservableValue<? extends Integer> from, Class<? extends Layer> receptor, IntegerProperty to) {
        from.addListener(new BindIntegerProperty(emitter, receptor, to));
    }

    public static <F, T> void bind(Class<? extends Layer> emitter, ObservableValue<F> from, Class<? extends Layer> receptor, ObjectProperty<T> to, Function<? super F, ? extends T> convert) {
        from.addListener(new BindCustom<>(emitter, receptor, to, convert));
        to.setValue(convert.apply(from.getValue()));
    }
}
