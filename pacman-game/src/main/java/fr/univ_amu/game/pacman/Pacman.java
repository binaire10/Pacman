package fr.univ_amu.game.pacman;


import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class Pacman extends Sprite {

    private int life;
    private final Scorer score;

    public Pacman(int life, Point2D p, float size) {
        super(new Rectangle2D(new Point2D(0, 0), new Point2D(size, size)), Color.YELLOW);
        setPosition(p);
        this.life = life;
        score = new Scorer();
    }

    public int getLife() {
        return this.life;
    }

    public void increaseScore(long amount) {
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