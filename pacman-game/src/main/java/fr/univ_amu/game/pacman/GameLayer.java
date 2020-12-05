package fr.univ_amu.game.pacman;

import fr.univ_amu.game.IO.Input;
import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.graphic.engine.GraphicEngine;
import fr.univ_amu.game.math.Point2D;

import java.util.ArrayList;
import java.util.List;

public class GameLayer implements Layer {

    private Pacman player;
    private List<Ghost> enemies;
    private List<Wall> walls;
    private List<Pellet> pellet;
    private List<PowerPellet> power;

    @Override
    public void onAttach() {
        this.player = new Pacman(3, new Point2D(0.125f,0.125f));
        GraphicEngine.getEngine().add(GameLayer.class, player.getSprite(), -1);
        this.enemies = new ArrayList<Ghost>();
        for(float[] color : List.of(Color.CYAN, Color.BLUE, Color.GREEN, Color.RED)){
            this.enemies.add(new Ghost(color, new Point2D(-0.125f,-0.125f)));
        }
        for(Ghost enemy: enemies){
            GraphicEngine.getEngine().add(GameLayer.class, enemy.getSprite(), -0.75f);
        }
        this.walls = new ArrayList<Wall>();
        String[] wallPosition = {"wwwwwwwwwww",
                                 "w         w",
                                 "w         w",
                                 "w         w",
                                 "w         w",
                                 "w         w",
                                 "w         w",
                                 "w         w",
                                 "w         w",
                                 "wwwwwwwwwww"};
        for(int i = 0; i < wallPosition.length; i++){
            float posI = (float)i/wallPosition.length*2f-1f;
            for(int j = 0; j < wallPosition[0].length(); j++){
                if(wallPosition[i].charAt(j) == 'w'){
                    float posJ = (float)j/wallPosition[0].length()*2f-1f;
                    this.walls.add(new Wall(new Point2D(posJ, posI)));
                }
            }
        }

        for(Wall wall:walls){
            GraphicEngine.getEngine().add(GameLayer.class, wall.getSprite(), -0.75f);
        }
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onUpdate(double timestep) {
        Input inst = Input.getInstance();
        float t = (float) timestep;
        player.getSprite().setPosition(
                player.getSprite().getPosition().sum(new Point2D(
                        ((inst.Keys.get(KeyCode.Right) ? 1 : 0) - (inst.Keys.get(KeyCode.Left) ? 1 : 0)) * t,
                        ((inst.Keys.get(KeyCode.Up) ? 1 : 0) - (inst.Keys.get(KeyCode.Down) ? 1 : 0)) * t
                ))
        );
    }

    @Override
    public void onEvent(Event e) {

    }
}
