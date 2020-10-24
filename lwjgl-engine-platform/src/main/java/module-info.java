module lwjgl.engine.platform {
    requires kernel.core;
    requires org.lwjgl;
    requires org.lwjgl.opengl;
    requires org.lwjgl.glfw;
    exports fr.univ_amu.game.lwjgl.render;
    exports fr.univ_amu.game.lwjgl;
    provides fr.univ_amu.game.core.GraphicPlatform with fr.univ_amu.game.lwjgl.LWJGLPlatform;
}