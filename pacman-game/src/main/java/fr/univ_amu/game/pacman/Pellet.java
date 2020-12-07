package fr.univ_amu.game.pacman;


import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class Pellet extends GiveScore {
    public Pellet(Point2D p, float size) {
        super(new Rectangle2D(new Point2D(0, 0), new Point2D(size, size)), Color.WHITE, 10);
        setPosition(p);
    }
}
