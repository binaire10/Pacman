package fr.univ_amu;

import fr.univ_amu.game.math.Vec;
import fr.univ_amu.game.render.Texture2D;

import java.util.ArrayList;

public class Animation <T> {
    public ArrayList<T> animation;
    public double duration;

    public Animation(ArrayList<T> anim , double duration) {
        this.animation = anim;
        this.duration = duration;

    }
    public Animation(double duration){
        animation = new ArrayList<T>();
        this.duration = duration;
    }

    public T getFrame(double time){
        return animation.get( (int) (( time%(animation.size()* duration)) /duration));
    }
}
