package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class Wall extends Entity{

    private static Sprite createSprite(Point2D p){
        return new Sprite(new Rectangle2D(p, 0.20f,0.20f), Color.GRAY);
    }

    public Wall(Point2D p) {
        super(Wall.createSprite(p));
    }
}
