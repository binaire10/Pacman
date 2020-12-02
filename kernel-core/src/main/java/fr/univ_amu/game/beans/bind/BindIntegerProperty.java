package fr.univ_amu.game.beans.bind;

import fr.univ_amu.game.beans.ChangeValueListener;
import fr.univ_amu.game.beans.IntegerProperty;
import fr.univ_amu.game.beans.ObservableValue;
import fr.univ_amu.game.core.Layer;

public class BindIntegerProperty implements ChangeValueListener<Integer> {
    private final Class<? extends Layer> source;
    private final Class<? extends Layer> destination;
    private final IntegerProperty value;

    public BindIntegerProperty(Class<? extends Layer> source, Class<? extends Layer> destination, IntegerProperty value) {
        this.source = source;
        this.destination = destination;
        this.value = value;
    }

    @Override
    public void update(ObservableValue<? extends Integer> observable, Integer from, Integer to) {
        value.setValue(to);
    }
}
