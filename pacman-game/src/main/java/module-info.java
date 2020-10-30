module pacman.game {
    requires kernel.core;
    requires graphic.engine;
    provides fr.univ_amu.game.core.Layer with fr.univ_amu.game.pacman.ExampleLayer, fr.univ_amu.game.pacman.EventLogger;
}