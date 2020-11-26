package fr.univ_amu.game.beans.bind;

import fr.univ_amu.game.beans.ChangeValueListener;
import fr.univ_amu.game.beans.FloatProperty;
import fr.univ_amu.game.beans.ObservableValue;
import fr.univ_amu.game.core.Layer;

public class BindFloatProperty implements ChangeValueListener<Float> {
    private final Class<? extends Layer> source;
    private final Class<? extends Layer> destination;
    private final FloatProperty value;

    public BindFloatProperty(Class<? extends Layer> source, Class<? extends Layer> destination, FloatProperty value) {
        this.source = source;
        this.destination = destination;
        this.value = value;
    }

    @Override
    public void update(ObservableValue<? extends Float> observable, Float from, Float to) {
        value.setValue(to);
    }
}
