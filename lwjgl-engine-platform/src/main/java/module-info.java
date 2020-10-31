module lwjgl.engine.platform {
    requires kernel.core;
    requires org.lwjgl;
    requires org.lwjgl.opengl;
    requires org.lwjgl.glfw;
    requires org.lwjgl.natives;
    requires org.lwjgl.glfw.natives;
    requires org.lwjgl.opengl.natives;
    requires org.lwjgl.stb;
    requires org.lwjgl.stb.natives;
    provides fr.univ_amu.game.core.GraphicPlatform with fr.univ_amu.game.lwjgl.LWJGLPlatform;
    provides fr.univ_amu.game.core.Layer with fr.univ_amu.game.lwjgl.LWJGLLayer;
}