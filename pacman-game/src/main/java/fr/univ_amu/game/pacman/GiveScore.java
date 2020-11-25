package fr.univ_amu.game.pacman;

import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.physics.PhysicEntity;

public abstract class GiveScore extends Entity{

    private int score;

    protected GiveScore(int score, PhysicEntity physical, QuadEntity graphical){
        super(physical, graphical);
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }
}
