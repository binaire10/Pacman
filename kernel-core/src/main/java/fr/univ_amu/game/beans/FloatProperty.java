package fr.univ_amu.game.beans;

import java.util.ArrayList;
import java.util.Collection;

public class FloatProperty implements ObservableValue<Float> {
    private final Collection<ChangeValueListener<? super Float>> listeners = new ArrayList<>();
    private float value;

    public FloatProperty() {
    }

    public FloatProperty(float value) {
        this.value = value;
    }

    @Override
    public Float getValue() {
        return value;
    }

    public void setValue(float value) {
        float old = this.value;
        this.value = value;
        notify(value, old);
    }

    private void notify(float value, float old) {
        for (var listener : listeners) {
            listener.update(this, old, value);
            if (this.value != value)
                break;
        }
    }

    @Override
    public void addListener(ChangeValueListener<? super Float> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ChangeValueListener<? super Float> listener) {
        listeners.remove(listener);
    }

    @Override
    public String toString() {
        return "FloatProperty{" +
                "value=" + value +
                '}';
    }
}
