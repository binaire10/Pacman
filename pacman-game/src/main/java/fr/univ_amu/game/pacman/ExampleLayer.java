package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.loader.GameplayLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.graphic.engine.GraphicEngine;
import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.math.VectorUtility;
import fr.univ_amu.game.physics.*;
import fr.univ_amu.game.render.Texture2D;
import fr.univ_amu.game.util.Utility;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@GameplayLayer
public class ExampleLayer implements Layer {
    //    private Window window;
    Texture2D texture2D;

    @Override
    public void onAttach() {
        System.out.println("Run ExampleLayer");
        try {
            texture2D = Platform.load_texture(Utility.readFile(ExampleLayer.class.getResource("Checkerboard.png")));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        /*List<QuadEntity> entities = new ArrayList<>(33 * 33);
        for (int i = 0; i < 33; i++)
            for (int j = 0; j < 33; j++)
                entities.add(new QuadEntity(VectorUtility.make_vec4((i - 16f) * 0.05f, (j - 16f) * 0.05f, 0), VectorUtility.make_vec2(0.009f * 5, 0.009f * 5), VectorUtility.make_vec4(1, 0, 1, 1)));

        entities.add(new QuadEntity(VectorUtility.make_vec4(0, 0, 0.1f), VectorUtility.make_vec2(4, 4), texture2D));*/
        GraphicEngine.getEngine().show();
        //GraphicEngine.getEngine().getGraphicEntities().addAll(entities);

        float[] position1 = VectorUtility.make_vec4(2, 0, 0.1f);
        QuadEntity q1 = new QuadEntity(position1, VectorUtility.make_vec2(1, 1), texture2D);
        Rectangle rec1 = new Rectangle(VectorUtility.make_vec2(0.5f + 2, 0.5f), VectorUtility.make_vec2(-0.5f + 2, -0.5f));
        PhysicMoveEntity pe1 = new PhysicMoveEntity(VectorUtility.make_vec2(2, 0), VectorUtility.make_vec2(-1e-4f, 0), rec1);

        float[] position2 = VectorUtility.make_vec4(-2, 0, 0.1f);
        QuadEntity q2 = new QuadEntity(position2, VectorUtility.make_vec2(1, 1), texture2D);
        Rectangle rec2 = new Rectangle(VectorUtility.make_vec2(0.5f - 2, 0.5f), VectorUtility.make_vec2(-0.5f - 2, -0.5f));
        PhysicMoveEntity pe2 = new PhysicMoveEntity(VectorUtility.make_vec2(-2, 0), VectorUtility.make_vec2(1e-4f, 0), rec2);


        pe1.addPositionListener(new PositionListener() {
            @Override
            public void update(float[] position) {
                float[] newPos = VectorUtility.make_vec4(position[0], position[1], 0.1f);
                q1.setPosition(newPos);
            }
        });
        pe2.addPositionListener(new PositionListener() {
            @Override
            public void update(float[] position) {
                float[] newPos = VectorUtility.make_vec4(position[0], position[1], 0.1f);
                q2.setPosition(newPos);
            }
        });

        PhysicLayer.getEngine().addPhysicEntity(pe1);
        PhysicLayer.getEngine().addPhysicEntity(pe2);

        PhysicLayer.getEngine().addCollideListener(new CollideListener() {
            @Override
            public void collideBetween(PhysicEntity p1, PhysicEntity p2) {
                pe1.setPosition(VectorUtility.make_vec2(position1));
                pe2.setPosition(VectorUtility.make_vec2(position2));
                q1.setPosition(position1);
                q2.setPosition(position2);
            }
        });

        GraphicEngine.getEngine().getGraphicEntities().add(q1);
        GraphicEngine.getEngine().getGraphicEntities().add(q2);


    }

    @Override
    public void onUpdate(double timestep) {
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
