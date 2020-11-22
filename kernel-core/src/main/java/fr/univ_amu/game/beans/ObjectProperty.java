package fr.univ_amu.game.beans;

import java.util.ArrayList;
import java.util.Collection;

public class ObjectProperty<T> implements ObservableValue<T> {
    private final Collection<ChangeValueListener<? super T>> listeners = new ArrayList<>();
    private T value = null;

    public ObjectProperty() {
    }

    public ObjectProperty(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        T old = this.value;
        this.value = value;
        for (var listener : listeners) {
            listener.update(this, old, value);
            if (!this.value.equals(value))
                break;
        }
    }

    @Override
    public void addListener(ChangeValueListener<? super T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ChangeValueListener<? super T> listener) {
        listeners.remove(listener);
    }
}
