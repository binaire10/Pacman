package fr.univ_amu.game.pacman;

import fr.univ_amu.game.beans.Binding;
import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;

public class SuperPacman extends Sprite {
    private final Pacman pacman;

    public SuperPacman(Pacman pacman) {
        super(new Rectangle2D(Point2D.ZERO, Point2D.ZERO), Color.RED);
        this.pacman = pacman;
        Binding.bindBidirectional(null, pacman.getShapeProperty(), null, getShapeProperty());
        Binding.bindBidirectional(null, pacman.getSpeedProperty(), null, getSpeedProperty());
    }

    public Pacman getPacman() {
        return pacman;
    }
}
