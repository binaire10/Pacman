import fr.univ_amu.game.core.Layer;

module pacman.game {
    requires kernel.core;
    requires graphic.engine;
    requires physics.engine;
    requires javafx.platform;
    provides Layer with fr.univ_amu.game.pacman.ExampleLayer;
}