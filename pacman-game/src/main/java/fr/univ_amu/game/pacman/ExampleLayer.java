package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.*;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.graphic.camera.OrthographicCamera;
import fr.univ_amu.game.graphic.render2D.BatchRender2D;
import fr.univ_amu.game.math.Vec;
import fr.univ_amu.game.render.Texture2D;

import java.io.IOException;
import java.net.URISyntaxException;

public class ExampleLayer implements Layer {
    private Window window;
    private BatchRender2D render2D;
    private OrthographicCamera camera;
    private Texture2D texture;

    @Override
    public void onAttach() {
        window = Platform.create_window("pacman", 720, 400);
        camera = new OrthographicCamera((float) window.getWidth() / window.getHeight());

        try {
            texture = Platform.load_texture(Utility.readFile(ExampleLayer.class.getResource("Checkerboard.png")));
            render2D = new BatchRender2D();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate(double timestep) {
        Platform.getRenderCommand().clear();
        camera.setRatio((float) window.getWidth() / window.getHeight());

        render2D.begin(camera);
        for (int i = 0; i < 33; i++) {
            for (int j = 0; j < 33; j++) {
                render2D.drawQuad(Vec.make_vec4((i - 16f) * 0.1f, (j - 16f) * 0.1f, 0), Vec.make_vec2(0.09f, 0.09f), Vec.make_vec4(1, 0, 1, 1));
            }
        }
        render2D.drawQuad(Vec.make_vec4(0, 0, 0.1f), Vec.make_vec2(4, 4), texture, 5);
        render2D.end();

        window.swap();
    }

    @Override
    public void onEvent(Event e) {
        new EventDispatch(e).dispatch(WindowCloseEvent.class, f -> Platform.shutdown());
    }

    @Override
    public void onDetach() {
        System.out.println("Stop Example");
        try {
            render2D.close();
            texture.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
