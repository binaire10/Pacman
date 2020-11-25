package fr.univ_amu.game.pacman;

import fr.univ_amu.game.IO.Input;
import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.core.loader.GameplayLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.graphic.engine.GraphicEngine;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Rectangle2D;
import fr.univ_amu.game.math.VectorUtility;
import fr.univ_amu.game.physics.CollideListener;
import fr.univ_amu.game.physics.PhysicEntity;
import fr.univ_amu.game.physics.PhysicLayer;
import fr.univ_amu.game.render.Texture2D;
import fr.univ_amu.game.util.Utility;

import java.io.IOException;
import java.net.URISyntaxException;

@GameplayLayer
public class ExampleLayer implements Layer {
    //    private Window window;
    Texture2D texture2D;
    Sprite player;

    @Override
    public void onAttach() {
        System.out.println("Run ExampleLayer");
        try {
            texture2D = Platform.load_texture(Utility.readFile(ExampleLayer.class.getResource("Checkerboard.png")));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        GraphicEngine.getEngine().show();
        player = new Sprite(new Rectangle2D(new Point2D(-0.5f, -0.5f), 1, 1), VectorUtility.make_vec4(1, 0, 0, 1));
        GraphicEngine.getEngine().add(ExampleLayer.class, player, 0);


        Rectangle2D shapeSprite = new Rectangle2D(
                new Point2D(-0.5f, -0.5f),// p1
                new Point2D(0.5f, 0.5f)   // p2
        );
        Point2D initialPositionSprite1 = new Point2D(2, 0);
        Point2D initialPositionSprite2 = new Point2D(-2, 0);

        Sprite sprite1 = new Sprite(shapeSprite, texture2D);
        sprite1.setSpeed(new Point2D(-1f, 0));
        sprite1.setPosition(initialPositionSprite1);

        Sprite sprite2 = new Sprite(shapeSprite, texture2D);
        sprite2.setSpeed(new Point2D(1f, 0));
        sprite2.setPosition(initialPositionSprite2);

        PhysicLayer.getEngine().add(ExampleLayer.class, sprite1);
        PhysicLayer.getEngine().add(ExampleLayer.class, sprite2);

        PhysicLayer.getEngine().addCollideListener(new CollideListener() {
            @Override
            public void collideBetween(PhysicEntity p1, PhysicEntity p2) {
                sprite1.setPosition(initialPositionSprite1);
                sprite2.setPosition(initialPositionSprite2);
            }
        });

        GraphicEngine.getEngine().add(ExampleLayer.class, sprite1, 0.1f);
        GraphicEngine.getEngine().add(ExampleLayer.class, sprite2, 0.1f);
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
    }

    @Override
    public void onEvent(Event e) {
        Utility.dispatch(e, WindowCloseEvent.class, f -> Platform.shutdown());
    }

    @Override
    public void onDetach() {
        System.out.println("Stop Example");
        try {
            texture2D.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
