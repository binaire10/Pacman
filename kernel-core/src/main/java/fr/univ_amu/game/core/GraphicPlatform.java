package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.render.RenderCommand;
import fr.univ_amu.game.render.Texture2D;

import java.nio.ByteBuffer;

public interface GraphicPlatform {
    void processEvent();

    void dispatch(Event event);

    Window create_window(String title, int width, int height);

    void clear();

    LayerStack<Layer> getLayerStack();

    RenderCommand getRenderCommand();

    Texture2D load_texture(ByteBuffer data);

    Texture2D make_texture(int w, int h);

    void startMainThread(Runnable runnable);
}
