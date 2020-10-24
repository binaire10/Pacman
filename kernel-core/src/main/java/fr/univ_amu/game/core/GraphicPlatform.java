package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.render.*;

import java.util.Map;

public interface GraphicPlatform {
    void processEvent();

    void dispatch(Event event);

    Window create_window(String title, int width, int height);

    IndexBuffer make_index(int[] index);

    VertexBuffer make_buffer(float[] data);

    VertexBuffer make_buffer(int capacity);

    VertexArray create_vertexArray();

    Material create_material(Map<ShaderType, String> shader);
}
