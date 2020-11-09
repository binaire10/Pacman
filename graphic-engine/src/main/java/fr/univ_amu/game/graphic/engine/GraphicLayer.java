package fr.univ_amu.game.graphic.engine;

import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.graphic.entities.QuadEntity;

import java.util.Collection;

public interface GraphicLayer {
    void onAttach();

    void onDetach();

    void onBegin(Window surface);

    void onRender(Collection<QuadEntity> graphicEntity);

    void onEnd();
}
