package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.math.Mat;
import fr.univ_amu.game.render.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Window window = Platform.create_window("pacman", 720, 400);

        VertexBuffer buffer = Platform.make_buffer(new float[]{
                -0.5f, -0.5f, 0.f, 1.f, 0.f, 1.f, 1.f,
                0.5f, -0.5f, 0.f, 0.f, 1.f, 1.f, 1.f,
                0.f, 0.5f, 0.f, 1.f, 1.f, 0.f, 1.f});

        IndexBuffer indexBuffer = Platform.make_index(new int[]{0, 1, 2});

        VertexArray vertexArray = Platform.create_vertexArray();
        vertexArray.setIndexBuffer(indexBuffer);
        vertexArray.setVertexBuffer(buffer, new BufferLayout(
                new BufferLayout.BufferEntry("a_Position", ShaderDataType.Float3),
                new BufferLayout.BufferEntry("a_Color", ShaderDataType.Float4)
        ));
        var code = Material.splitCode(new String(Main.class.getResourceAsStream("flatColor.glsl").readAllBytes()));
        var shaders = Platform.create_material(code);

        shaders.bind();
        shaders.uploadUniformMatrix4("u_transform", Mat.identity(4));

        while (!window.isClose()) {


            float ratio = (float) window.getWidth() / window.getHeight();
            var view = Mat.ortho(ratio, -ratio, -1, 1);

            window.swap();
            Platform.processEvent();
        }
    }
}
