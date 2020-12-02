package fr.univ_amu.game.pacman;


import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class Pellet extends GiveScore{

    private static Sprite createSprite(Point2D p){
        return new Sprite(new Rectangle2D(p, 0.25f,0.25f), Color.WHITE);
    }

    public Pellet(Point2D p){
        super(10, Pellet.createSprite(p));
    }
}
