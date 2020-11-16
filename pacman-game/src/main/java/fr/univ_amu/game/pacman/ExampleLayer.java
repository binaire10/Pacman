package fr.univ_amu.game.pacman;

import fr.univ_amu.Animation;
import fr.univ_amu.game.core.EventDispatch;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.graphic.camera.OrthographicCamera;
import fr.univ_amu.game.graphic.render2D.BatchRender2D;
import fr.univ_amu.game.math.Vec;
import fr.univ_amu.game.render.Texture2D;
import fr.univ_amu.game.util.Utility;

import java.io.IOException;
import java.net.URISyntaxException;

public class ExampleLayer implements Layer {
    private Window window;
    private BatchRender2D render2D;
    private OrthographicCamera camera;
    private Texture2D texture;

    @Override
    public void onAttach() {
        System.out.println("Run ExampleLayer");
        window = Platform.create_window("pacman", 1366, 768);
        camera = new OrthographicCamera((float) window.getWidth() / window.getHeight());

        try {
            texture = Platform.load_texture(Utility.readFile(ExampleLayer.class.getResource("Checkerboard.png")));
            render2D = new BatchRender2D();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        window.show();
    }

    @Override
    public void onUpdate(double timestep) {
        Platform.getRenderCommand().clear();
        camera.setRatio((float) window.getWidth() / window.getHeight());
        Texture2D texture2 = null;
        try {
             texture2 = Platform.load_texture(Utility.readFile(ExampleLayer.class.getResource("automne.png")));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Animation<float[]> anim =new Animation(1000);
        anim.addframe(Vec.make_vec4(0, 0, 1, 1));
        anim.addframe(Vec.make_vec4(1, 0, 1, 1));
        anim.addframe(Vec.make_vec4(0, 1, 1, 1));
        anim.addframe(Vec.make_vec4(1, 0.3f, 1, 1));
        Animation<Texture2D> anim2 = new Animation<>(1000);
        anim2.addframe(texture);
        anim2.addframe(texture2);

        render2D.begin(camera);
        for (int i = 0; i < 33; i++)
            for (int j = 0; j < 33; j++)
                render2D.drawQuad(Vec.make_vec4((i - 16f) * 0.05f, (j - 16f) * 0.05f, 0), Vec.make_vec2(0.009f * 5, 0.009f * 5), anim.getFrame(System.currentTimeMillis()));
        render2D.drawQuad(Vec.make_vec4(0, 0, 0.1f), Vec.make_vec2(4, 4), anim2.getFrame(System.currentTimeMillis()), 6);

        //render2D.drawQuad(Vec.make_vec4(0,0,0),Vec.make_vec2(1,1),anim2.getFrame(System.currentTimeMillis()),5);
        render2D.end();

    }

    @Override
    public void afterUpdate() {
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
