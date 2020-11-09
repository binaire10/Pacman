package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.UpdatableLayer;
import fr.univ_amu.game.core.loader.GameplayLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.graphic.engine.GraphicEngine;
import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.math.VectorUtility;
import fr.univ_amu.game.render.Texture2D;
import fr.univ_amu.game.util.Utility;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@GameplayLayer
public class ExampleLayer implements UpdatableLayer {
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
        List<QuadEntity> entities = new ArrayList<>(33 * 33);
        for (int i = 0; i < 33; i++)
            for (int j = 0; j < 33; j++)
                entities.add(new QuadEntity(VectorUtility.make_vec4((i - 16f) * 0.05f, (j - 16f) * 0.05f, 0), VectorUtility.make_vec2(0.009f * 5, 0.009f * 5), VectorUtility.make_vec4(1, 0, 1, 1)));
        entities.add(new QuadEntity(VectorUtility.make_vec4(0, 0, 0.1f), VectorUtility.make_vec2(4, 4), texture2D));
        GraphicEngine.getEngine().show();
        GraphicEngine.getEngine().getGraphicEntities().addAll(entities);
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
