package fr.univ_amu.game.pacman;

public class Score {
    private long score; // Uses a long to have the same maximum number as a unsigned int, which does not exist in Java.

    public Score(){
        this.score = 0;
    }

    public void update(long addedAmount){
        if(addedAmount < 0){
            System.err.println("You cannot add negative score.");
            return ;
        }
        this.score += addedAmount;
    }

    public long getScore(){
        return this.score;
    }
}
