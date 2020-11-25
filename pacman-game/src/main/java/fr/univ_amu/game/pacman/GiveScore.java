package fr.univ_amu.game.pacman;

public abstract class GiveScore {

    private int score;

    protected GiveScore(int score){
        this.score = score;
    }

    protected int getScore(){
        return this.score;
    }
}
