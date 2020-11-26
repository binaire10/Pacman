package fr.univ_amu.game.beans;

public interface ObservableValue<T> {
    T getValue();

    void addListener(ChangeValueListener<? super T> listener);

    void removeListener(ChangeValueListener<? super T> listener);
}
