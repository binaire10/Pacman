import fr.univ_amu.game.core.UpdatableLayer;
import fr.univ_amu.game.graphic.engine.GraphicEngine;

module graphic.engine {
    requires kernel.core;
    requires java.desktop;
    exports fr.univ_amu.game.graphic;
    exports fr.univ_amu.game.graphic.camera;
    exports fr.univ_amu.game.graphic.engine;
    exports fr.univ_amu.game.graphic.entities;
    uses fr.univ_amu.game.graphic.engine.GraphicLayer;
    provides UpdatableLayer with GraphicEngine;
}