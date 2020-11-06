package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.EventDispatch;
import fr.univ_amu.game.core.MainLayer;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.loader.GameplayLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.graphic.engine.GraphicEngineLayer;
import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.math.Vec;
import fr.univ_amu.game.render.Texture2D;
import fr.univ_amu.game.util.Utility;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@GameplayLayer
public class ExampleLayer implements MainLayer {
    //    private Window window;
    Texture2D texture2D;

    @Override
    public void onAttach() {
        System.out.println("Run ExampleLayer");
        try {
            texture2D = GraphicEngineLayer.getEngine().load_texture(Utility.readFile(ExampleLayer.class.getResource("Checkerboard.png")));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        List<QuadEntity> entities = new ArrayList<>(33 * 33);
        for (int i = 0; i < 33; i++)
            for (int j = 0; j < 33; j++)
                entities.add(new QuadEntity(Vec.make_vec4((i - 16f) * 0.05f, (j - 16f) * 0.05f, 0), Vec.make_vec2(0.009f * 5, 0.009f * 5), Vec.make_vec4(1, 0, 1, 1)));
        entities.add(new QuadEntity(Vec.make_vec4(0, 0, 0.1f), Vec.make_vec2(4, 4), texture2D));
        GraphicEngineLayer.getEngine().getGraphicEntities().addAll(entities);
    }

    @Override
    public void onUpdate(double timestep) {
    }

    @Override
    public void afterUpdate() {
    }

    @Override
    public void onEvent(Event e) {
        new EventDispatch(e).dispatch(WindowCloseEvent.class, f -> Platform.shutdown());
    }

    @Override
    public void onDetach() {
        GraphicEngineLayer.getEngine().free_texture(texture2D);
        System.out.println("Stop Example");
    }
}
