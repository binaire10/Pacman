package fr.univ_amu.game.beans;

import java.util.ArrayList;
import java.util.Collection;

public class IntegerProperty implements ObservableValue<Integer> {
    private final Collection<ChangeValueListener<? super Integer>> listeners = new ArrayList<>();
    private int value;

    public IntegerProperty() {
    }

    public IntegerProperty(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        int old = this.value;
        this.value = value;
        notify(value, old);
    }

    private void notify(int value, int old) {
        for (var listener : listeners) {
            listener.update(this, old, value);
            if (this.value != value)
                break;
        }
    }

    @Override
    public void addListener(ChangeValueListener<? super Integer> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ChangeValueListener<? super Integer> listener) {
        listeners.remove(listener);
    }

    @Override
    public String toString() {
        return "IntegerProperty{" +
                "value=" + value +
                '}';
    }
}
