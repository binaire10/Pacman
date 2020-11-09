import fr.univ_amu.game.core.Layer;

module pacman.game {
    requires kernel.core;
    requires graphic.engine;
    provides Layer with fr.univ_amu.game.pacman.ExampleLayer;
}