package fr.univ_amu.game.graphic.engine;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.graphic.entities.GraphicEntity;

public interface GraphicLayer extends Layer {
    void onBegin(Window surface);

    void onRender(GraphicEntity graphicEntity);

    void onEnd();
}
