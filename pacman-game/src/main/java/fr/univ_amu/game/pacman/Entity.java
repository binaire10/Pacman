package fr.univ_amu.game.pacman;


import fr.univ_amu.game.core.Sprite;

public abstract class Entity {

    private Sprite sprite;

    protected Entity(Sprite sprite){
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return this.sprite;
    }
}
