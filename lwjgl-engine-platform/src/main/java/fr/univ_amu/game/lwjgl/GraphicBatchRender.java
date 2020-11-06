package fr.univ_amu.game.lwjgl;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.core.loader.EngineLayer;
import fr.univ_amu.game.graphic.camera.OrthographicCamera;
import fr.univ_amu.game.graphic.engine.GraphicLayer;
import fr.univ_amu.game.graphic.entities.GraphicEntity;
import fr.univ_amu.game.graphic.entities.QuadEntity;
import fr.univ_amu.game.graphic.render2D.BatchRender2D;
import fr.univ_amu.game.render.Texture2D;

import java.io.IOException;

@EngineLayer
public class GraphicBatchRender implements GraphicLayer {
    private BatchRender2D render2D;
    private OrthographicCamera camera;
    private Texture2D texture;

    @Override
    public void onRender(GraphicEntity graphicEntity) {
        if (graphicEntity instanceof QuadEntity) {
            QuadEntity entity = (QuadEntity) graphicEntity;
            render2D.drawQuad(entity.getPosition(), entity.getSize(), entity.getColor(), entity.getTexture(), 1);
        }
    }

    @Override
    public void onAttach() {
        camera = new OrthographicCamera(1f);
        try {
            render2D = new BatchRender2D();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        try {
            render2D.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBegin(Window surface) {
        Platform.getRenderCommand().clear();
        camera.setRatio((float) surface.getWidth() / surface.getHeight());
        render2D.begin(camera);
    }

    @Override
    public void onEnd() {
        render2D.end();
    }
}
