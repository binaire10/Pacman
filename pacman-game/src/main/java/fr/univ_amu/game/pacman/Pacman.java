package fr.univ_amu.game.pacman;


import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class Pacman extends Entity{

    private int life;
    private Scorer score;

    private static Sprite createSprite(Point2D p){
        return new Sprite(new Rectangle2D(p, 1,1), Color.YELLOW);
    }

    public Pacman(int life, Point2D p){
        super(Pacman.createSprite(p));
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