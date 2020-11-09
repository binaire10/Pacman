import fr.univ_amu.game.core.UpdatableLayer;

module pacman.game {
    requires kernel.core;
    requires graphic.engine;
    provides UpdatableLayer with fr.univ_amu.game.pacman.ExampleLayer;
}