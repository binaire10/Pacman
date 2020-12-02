package fr.univ_amu.game.pacman;


import fr.univ_amu.game.core.Sprite;

public abstract class GiveScore extends Entity{

    private int score;

    protected GiveScore(int score, Sprite sprite){
        super(sprite);
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }
}
