package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.graphic.engine.GraphicEngine;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;
import fr.univ_amu.game.physics.CollideListener;
import fr.univ_amu.game.physics.PhysicEntity;
import fr.univ_amu.game.physics.PhysicLayer;

import java.util.List;

public class Collision implements CollideListener {

    private final Pacman player;
    private final List<Ghost> enemies;
    private PowerPellet power;
    private Wall wall;

    public Collision(Pacman player, List<Ghost> enemies) {
        this.player = player;
        this.enemies = enemies;
    }

    public void pacmanGhost(Ghost eaten) {
        if (eaten.getVulnerability()) {
            player.increaseScore(eaten.getScore());
            eaten.unsetVulnerability();
        }
        else{
            player.decreaseLife(1);
            for (Ghost enemy : enemies) enemy.setPosition(new Point2D(-0.125f, -0.125f));
        }
    }

    @Override
    public void collideBetween(PhysicEntity p1, Rectangle2D oldObj1, PhysicEntity p2, Rectangle2D oldObj2) {
        System.out.print(p1.getSprite());
        System.out.print(" ");
        System.out.println(p2.getSprite());

        if (p1.getSprite() instanceof Pacman && p2.getSprite() instanceof Pellet) {
            ((Pacman) p1.getSprite()).increaseScore(((Pellet) p2.getSprite()).getScore());
            GraphicEngine.getEngine().remove(p2.getSprite());
            PhysicLayer.getEngine().remove(p2.getSprite());
        } else if (p2.getSprite() instanceof Pacman && p1.getSprite() instanceof Pellet) {
            ((Pacman) p2.getSprite()).increaseScore(((Pellet) p1.getSprite()).getScore());
            GraphicEngine.getEngine().remove(p1.getSprite());
            PhysicLayer.getEngine().remove(p1.getSprite());
        }

        if (p1.getSprite() instanceof SuperPacman && p2.getSprite() instanceof Pellet) {
            ((SuperPacman) p1.getSprite()).getPacman().increaseScore(((Pellet) p2.getSprite()).getScore());
            GraphicEngine.getEngine().remove(p2.getSprite());
            PhysicLayer.getEngine().remove(p2.getSprite());
        } else if (p2.getSprite() instanceof SuperPacman && p1.getSprite() instanceof Pellet) {
            ((SuperPacman) p2.getSprite()).getPacman().increaseScore(((Pellet) p1.getSprite()).getScore());
            GraphicEngine.getEngine().remove(p1.getSprite());
            PhysicLayer.getEngine().remove(p1.getSprite());
        }

        if (p1.getSprite() instanceof Pacman && p2.getSprite() instanceof PowerPellet) {
            ((Pacman) p1.getSprite()).increaseScore(((PowerPellet) p2.getSprite()).getScore());
            GraphicEngine.getEngine().remove(p2.getSprite());
            PhysicLayer.getEngine().remove(p2.getSprite());

            GraphicEngine.getEngine().remove(player);
            PhysicLayer.getEngine().remove(player);

            SuperPacman superPacman = new SuperPacman(player);
            GraphicEngine.getEngine().add(GameLayer.class, superPacman, -1f);
            PhysicLayer.getEngine().add(GameLayer.class, superPacman);
        } else if (p2.getSprite() instanceof Pacman && p1.getSprite() instanceof PowerPellet) {
            ((Pacman) p2.getSprite()).increaseScore(((PowerPellet) p1.getSprite()).getScore());
            GraphicEngine.getEngine().remove(p1.getSprite());
            PhysicLayer.getEngine().remove(p1.getSprite());
            GraphicEngine.getEngine().remove(player);
            PhysicLayer.getEngine().remove(player);

            SuperPacman superPacman = new SuperPacman(player);
            GraphicEngine.getEngine().add(GameLayer.class, superPacman, -1f);
            PhysicLayer.getEngine().add(GameLayer.class, superPacman);
        }

        if (p1.getSprite() instanceof Pacman && p2.getSprite() instanceof Ghost) {
            Platform.shutdown();
        } else if (p2.getSprite() instanceof Pacman && p1.getSprite() instanceof Ghost) {
            Platform.shutdown();
        }
    }
}
