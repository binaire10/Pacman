package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class Ghost extends GiveScore {

    private boolean isVulnerable;

    private static Sprite createSprite(Point2D p, float[] color){
        return new Sprite(new Rectangle2D(p, 1,1), color);
    }

    public Ghost(float[] color, Point2D p) {
        super(500, Ghost.createSprite(p, color));
        this.isVulnerable = false;
    }

    public void setVulnerability(){this.isVulnerable = true;}

    public void unsetVulnerability(){this.isVulnerable = false;}

    public boolean getVulnerability() {
        return this.isVulnerable;
    }
}
