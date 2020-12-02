package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class PowerPellet extends GiveScore {

    private static Sprite createSprite(Point2D p){
        return new Sprite(new Rectangle2D(p, 0.5f,0.5f), Color.WHITE);
    }

    public PowerPellet(Point2D p) {
        super(100, PowerPellet.createSprite(p));
    }
}
