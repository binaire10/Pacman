package fr.univ_amu.game.pacman;

import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;
import fr.univ_amu.game.physics.CollideListener;
import fr.univ_amu.game.physics.PhysicEntity;

import java.util.List;

public class Collision implements CollideListener {

    private Pacman player;
    private List<Ghost> enemies;
    private Pellet pellet;
    private PowerPellet power;

    public void pacmanGhost(Ghost eaten){
        if(eaten.getVulnerability()){
            player.increaseScore(eaten.getScore());
            eaten.unsetVulnerability();
        }
        else{
            player.decreaseLife(1);
            for (Ghost enemy : enemies) enemy.getSprite().setPosition(new Point2D(0, 0));
        }
    }

    public void pacmanPellet(){
        player.increaseScore(pellet.getScore());
    }

    public void pacmanPowerPellet(){
        for(Ghost enemy: enemies)
            enemy.setVulnerability();
    }

    @Override
    public void collideBetween(PhysicEntity p1, Rectangle2D oldObj1, PhysicEntity p2, Rectangle2D oldObj2) {

        if(p1.getSprite() == player.getSprite() && p2.getSprite() == pellet.getSprite())
            pacmanPellet();
        else if(p2.getSprite() == pellet.getSprite() && p1.getSprite() == player.getSprite())
            pacmanPellet();

        if(p1.getSprite() == player.getSprite() && p2.getSprite() == power.getSprite())
            pacmanPowerPellet();
        else if(p2.getSprite() == player.getSprite() && p1.getSprite() == power.getSprite())
            pacmanPowerPellet();

        for(Ghost enemy : enemies){
            if (p1.getSprite() == player.getSprite() && p2.getSprite() == enemy.getSprite())
                pacmanGhost(enemy);
            else if (p2.getSprite() == player.getSprite() && p1.getSprite() == enemy.getSprite())
                pacmanGhost(enemy);
        }
    }
}
