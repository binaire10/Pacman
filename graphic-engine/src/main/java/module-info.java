module graphic.engine {
    requires kernel.core;
    requires java.desktop;
    exports fr.univ_amu.game.graphic;
    exports fr.univ_amu.game.graphic.camera;
    exports fr.univ_amu.game.graphic.engine;
    exports fr.univ_amu.game.graphic.entities;
    exports fr.univ_amu.game.graphic.render2D;
    uses fr.univ_amu.game.graphic.engine.GraphicLayer;
    provides fr.univ_amu.game.core.MainLayer with fr.univ_amu.game.graphic.engine.GraphicEngineLayer;
}