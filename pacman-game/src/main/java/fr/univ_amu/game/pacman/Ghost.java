package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Sprite;

public class Ghost extends GiveScore {

    private boolean isVulnerable;

    public Ghost(Sprite sprite) {
        super(500, sprite);
        this.isVulnerable = false;
    }

    public void setVulnerability(){this.isVulnerable = true;}

    public void unsetVulnerability(){this.isVulnerable = false;}

    public boolean getVulnerability() {
        return this.isVulnerable;
    }
}
