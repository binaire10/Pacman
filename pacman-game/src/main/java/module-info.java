module pacman.game {
    requires kernel.core;
    requires graphic.engine;
    provides fr.univ_amu.game.core.MainLayer with fr.univ_amu.game.pacman.ExampleLayer, fr.univ_amu.game.pacman.EventLogger;
}