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

    public void pacmanGhost(){
        player.decreaseLife(1);
        for(Ghost enemy: enemies) enemy.getPhysical().getSprite().setPosition(new Point2D(0,0));
    }

    public void pacmanPellet(){
        player.increaseScore(pellet.getScore());
    }

    @Override
    public void collideBetween(PhysicEntity p1, Rectangle2D oldObj1, PhysicEntity p2, Rectangle2D oldObj2) {

        if(p1.getSprite() == player.getPhysical().getSprite() && p2.getSprite() == pellet.getPhysical().getSprite())
            pacmanPellet();
        else if(p2.getSprite() == pellet.getPhysical().getSprite() && p1.getSprite() == player.getPhysical().getSprite())
            pacmanPellet();

        for(Ghost enemy : enemies){
            if (p1.getSprite() == player.getPhysical().getSprite() && p2.getSprite() == enemy.getPhysical().getSprite())
                pacmanGhost();
            else if (p2.getSprite() == player.getPhysical().getSprite() && p1.getSprite() == enemy.getPhysical().getSprite())
                pacmanGhost();
        }
    }
}
