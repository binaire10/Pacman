package fr.univ_amu.game.pacman;

import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class Ghost extends GiveScore {
    private boolean isVulnerable;

    public Ghost(float[] color, Point2D p, float size) {
        super(new Rectangle2D(new Point2D(0, 0), new Point2D(size, size)), color, 500);
        setPosition(p);
        this.isVulnerable = false;
    }

    public void setVulnerability() {
        this.isVulnerable = true;
    }

    public void unsetVulnerability() {
        this.isVulnerable = false;
    }

    public boolean getVulnerability() {
        return this.isVulnerable;
    }
}
