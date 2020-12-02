package fr.univ_amu.game.beans.bind;

import fr.univ_amu.game.beans.ChangeValueListener;
import fr.univ_amu.game.beans.ObjectProperty;
import fr.univ_amu.game.beans.ObservableValue;
import fr.univ_amu.game.core.Layer;

public class BindBidirectionalObjectProperty<T> implements ChangeValueListener<T> {
    private final Class<? extends Layer> sourceA;
    private final ObjectProperty<T> valueA;
    private final Class<? extends Layer> sourceB;
    private final ObjectProperty<T> valueB;
    private boolean isUpdating = false;
    private T valueUpdated;

    public BindBidirectionalObjectProperty(Class<? extends Layer> sourceA, ObjectProperty<T> valueA, Class<? extends Layer> sourceB, ObjectProperty<T> valueB) {
        this.sourceA = sourceA;
        this.valueA = valueA;
        this.sourceB = sourceB;
        this.valueB = valueB;
    }

    @Override
    public void update(ObservableValue<? extends T> observable, T from, T to) {
        if (isUpdating)
            return;

        isUpdating = true;

        if (observable.equals(valueA))
            valueB.setValue(to);
        else if (observable.equals(valueB))
            valueA.setValue(to);

        isUpdating = false;
    }
}
