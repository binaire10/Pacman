module lwjgl.engine.platform {
    requires kernel.core;
    requires transitive org.lwjgl;
    requires transitive org.lwjgl.opengl;
    requires transitive org.lwjgl.glfw;
    requires transitive org.lwjgl.natives;
    requires transitive org.lwjgl.glfw.natives;
    requires transitive org.lwjgl.opengl.natives;
    exports fr.univ_amu.game.lwjgl.render;
    exports fr.univ_amu.game.lwjgl;
    provides fr.univ_amu.game.core.GraphicPlatform with fr.univ_amu.game.lwjgl.LWJGLPlatform;
}