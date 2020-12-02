package fr.univ_amu.game.pacman;


import fr.univ_amu.game.core.Sprite;

public class Pacman extends Entity{

    private int life;
    private Scorer score;

    public Pacman(Sprite sprite, int life){
        super(sprite);
        this.life = life;
        score = new Scorer();
    }

    public int getLife(){
        return this.life;
    }

    public void increaseScore(long amount){
        this.score.addScore(amount);
    }

    public void decreaseLife(int amountToDecrease){
        if(amountToDecrease > this.life){
            this.life = 0;
            return;
        }
        this.life -= amountToDecrease;
    }
}