package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class PowerPellet extends GiveScore {

    public PowerPellet(Point2D p, float size) {
        super(new Rectangle2D(new Point2D(0, 0), new Point2D(size, size)), Color.WHITE, 100);
        setPosition(p);
    }
}
