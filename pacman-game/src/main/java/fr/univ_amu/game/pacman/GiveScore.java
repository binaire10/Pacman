package fr.univ_amu.game.pacman;


import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.math.Rectangle2D;
import fr.univ_amu.game.render.Texture2D;

public abstract class GiveScore extends Sprite {

    private final int score;

    public GiveScore(Rectangle2D shape, Texture2D texture, int score) {
        super(shape, texture);
        this.score = score;
    }

    public GiveScore(Rectangle2D shape, float[] color, int score) {
        super(shape, color);
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}
