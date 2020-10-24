package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.math.Mat;
import fr.univ_amu.game.math.Vec;
import fr.univ_amu.game.render.*;

import java.io.IOException;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryUtil.NULL;

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
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


            float ratio = (float) window.getWidth() / window.getHeight();
            var view = Mat.ortho(ratio, -ratio, -1, 1);
            shaders.uploadUniformMatrix4("u_viewProjection", view);


            vertexArray.bind();
            shaders.bind();
            shaders.uploadUniform("u_Color", Vec.make_vec4(1, 0, 1, 1));
            glDrawElements(GL_TRIANGLES, indexBuffer.count(), GL_UNSIGNED_INT, NULL);
            window.swap();
            Platform.processEvent();
        }
    }
}
