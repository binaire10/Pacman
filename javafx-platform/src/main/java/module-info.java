module javafx.platform {
    requires javafx.controls;
    requires graphic.engine;
    requires kernel.core;
    provides fr.univ_amu.game.core.GraphicPlatform with fr.univ_amu.game.javafx.JavaFXPlatform;
    provides fr.univ_amu.game.graphic.engine.GraphicLayer with fr.univ_amu.game.javafx.JavaFXLayer;
}