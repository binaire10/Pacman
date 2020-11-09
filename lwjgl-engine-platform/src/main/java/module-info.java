import fr.univ_amu.game.core.UpdatableLayer;
import fr.univ_amu.game.lwjgl.GraphicBatchRender;

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
    requires graphic.engine;
    provides fr.univ_amu.game.core.GraphicPlatform with fr.univ_amu.game.lwjgl.LWJGLPlatform;
    provides UpdatableLayer with fr.univ_amu.game.lwjgl.LWJGLLayer;
    provides fr.univ_amu.game.graphic.engine.GraphicLayer with GraphicBatchRender;
}