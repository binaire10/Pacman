import fr.univ_amu.game.core.UpdatableLayer;

open module kernel.core {
    exports fr.univ_amu.game.core;
    exports fr.univ_amu.game.core.loader;
    exports fr.univ_amu.game.event.application;
    exports fr.univ_amu.game.event.mouse;
    exports fr.univ_amu.game.event;
    exports fr.univ_amu.game.math;
    exports fr.univ_amu.game.render;
    exports fr.univ_amu.game.util;
    exports fr.univ_amu.graph;
    uses fr.univ_amu.game.core.GraphicPlatform;
    uses UpdatableLayer;
}