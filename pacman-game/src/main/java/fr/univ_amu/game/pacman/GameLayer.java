package fr.univ_amu.game.pacman;

import fr.univ_amu.game.IO.Input;
import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.graphic.Color;
import fr.univ_amu.game.graphic.camera.OrthographicCamera;
import fr.univ_amu.game.graphic.engine.GraphicEngine;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.physics.PhysicLayer;

import java.util.ArrayList;
import java.util.List;

public class GameLayer implements Layer {

    private Pacman player;
    private List<Ghost> enemies;
    private List<Wall> walls;
    private List<Pellet> pellet;
    private List<PowerPellet> power;
    private Collision collisions;

    @Override
    public void onAttach() {
        this.player = new Pacman(3, new Point2D(0.125f, 0.125f), 0.75f);
        this.pellet = new ArrayList<Pellet>();
        this.enemies = new ArrayList<Ghost>();
        this.walls = new ArrayList<Wall>();
        this.power = new ArrayList<PowerPellet>();
        GraphicEngine.getEngine().setCamera(new OrthographicCamera(1, 0, 5));
        String[] wallPosition = {
                "wwwwwwwwwww",
                "wgggggggGgw",
                "wgwwwgwwwgw",
                "wgggggggggw",
                "wwwgwffwgww",
                "wgggwwwgggw",
                "wgwgggggwgw",
                "wgwwwgwwwgw",
                "wgggggggPgw",
                "wwwwwwwwwww"};
        for (int i = 0; i < wallPosition.length; i++) {
            for (int j = 0; j < wallPosition[0].length(); j++) {
                if (wallPosition[i].charAt(j) == 'w') {
                    this.walls.add(new Wall(new Point2D(j - 5f, i - 5f), 0.99f));
                }
                if (wallPosition[i].charAt(j) == 'g') {
                    this.pellet.add(new Pellet(new Point2D(j - 5f, i - 5f), 0.2f));
                }
                if (wallPosition[i].charAt(j) == 'G') {
                    this.power.add(new PowerPellet(new Point2D(j - 5f, i - 5f), 0.4f));
                }
                if (wallPosition[i].charAt(j) == 'P') {
                    this.player = new Pacman(3, new Point2D(j - 5f, i - 5f), 0.75f);
                }
                if (wallPosition[i].charAt(j) == 'f') {
                    this.enemies.add(new Ghost(Color.CYAN, new Point2D(j - 5f, i - 5f), 0.8f));
                }
            }
        }

        GraphicEngine.getEngine().add(GameLayer.class, player, -1);
        PhysicLayer.getEngine().add(GameLayer.class, player);

        for (Pellet pellet : pellet) {
            GraphicEngine.getEngine().add(GameLayer.class, pellet, -0.7f);
            PhysicLayer.getEngine().add(GameLayer.class, pellet);
        }

        for (PowerPellet pellet : power) {
            GraphicEngine.getEngine().add(GameLayer.class, pellet, -0.7f);
            PhysicLayer.getEngine().add(GameLayer.class, pellet);
        }

        for (Ghost enemy : enemies) {
            GraphicEngine.getEngine().add(GameLayer.class, enemy, -0.75f);
            PhysicLayer.getEngine().add(GameLayer.class, enemy);
        }

        for (Wall wall : walls) {
            GraphicEngine.getEngine().add(GameLayer.class, wall, -0.75f);
            PhysicLayer.getEngine().add(GameLayer.class, wall);
        }
        this.collisions = new Collision(this.player, this.enemies);
        PhysicLayer.getEngine().addCollideListener(collisions);
//        Input.getInstance().
    }

    @Override
    public void onDetach() {
    }

    @Override
    public void onUpdate(double timestep) {
        Input inst = Input.getInstance();
        float t = (float) timestep;
        player.setPosition(
                player.getPosition().sum(new Point2D(
                        ((inst.Keys.get(KeyCode.Right) ? 1 : 0) - (inst.Keys.get(KeyCode.Left) ? 1 : 0)) * t,
                        ((inst.Keys.get(KeyCode.Up) ? 1 : 0) - (inst.Keys.get(KeyCode.Down) ? 1 : 0)) * t
                ))
        );
    }

    @Override
    public void afterUpdate() {
        //this.collisions.collideBetween();
    }

    @Override
    public void onEvent(Event e) {

    }
}
