package fr.univ_amu.game.beans;

public interface ChangeValueListener<T> {
    void update(ObservableValue<? extends T> observable, T from, T to);
}
