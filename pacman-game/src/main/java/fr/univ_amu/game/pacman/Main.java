package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.graphic.camera.OrthographicCamera;
import fr.univ_amu.game.math.Mat;
import fr.univ_amu.game.render.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static ByteBuffer read(URL file) throws URISyntaxException, IOException {
        var path = Path.of(file.toURI());
        return FileChannel.open(path).map(FileChannel.MapMode.READ_ONLY, 0, Files.size(path));
    }

    public static void main(String[] args) throws IOException {
        Window window = Platform.create_window("pacman", 720, 400);

        var code = Material.splitCode(new String(Main.class.getResourceAsStream("flatColor.glsl").readAllBytes()));
        var codeTex = Material.splitCode(new String(Main.class.getResourceAsStream("texture.glsl").readAllBytes()));

        try (
                Material shaders = Platform.create_material(code);
                VertexBuffer buffer = Platform.make_buffer(new float[]{
                        -0.5f, -0.5f, 0.f, 1.f, 0.f, 1.f, 1.f,
                        0.5f, -0.5f, 0.f, 0.f, 1.f, 1.f, 1.f,
                        0.f, 0.5f, 0.f, 1.f, 1.f, 0.f, 1.f});
                IndexBuffer indexBuffer = Platform.make_index(new int[]{0, 1, 2});
                VertexArray vertexArray = Platform.create_vertexArray();

                Material shadersQuad = Platform.create_material(codeTex);
                VertexBuffer bufferQuad = Platform.make_buffer(new float[]{
                        -0.5f, -0.5f, 0.f, 1.f, 0.f, 1.f, 1.f, 0, 0,
                        0.5f, -0.5f, 0.f, 0.f, 1.f, 1.f, 1.f, 1, 0,
                        0.5f, 0.5f, 0.f, 0.f, 1.f, 1.f, 1.f, 1, 1,
                        -0.5f, 0.5f, 0.f, 1.f, 1.f, 0.f, 1.f, 0, 1});
                IndexBuffer indexBufferQuad = Platform.make_index(new int[]{0, 1, 2, 2, 3, 0});
                VertexArray vertexArrayQuad = Platform.create_vertexArray();
                Texture2D texture = Platform.load_texture(read(Main.class.getResource("Checkerboard.png")));
        ) {
            vertexArray.setIndexBuffer(indexBuffer);
            vertexArray.setVertexBuffer(buffer, new BufferLayout(
                    new BufferLayout.BufferEntry("a_Position", ShaderDataType.Float3),
                    new BufferLayout.BufferEntry("a_Color", ShaderDataType.Float4)
            ));

            vertexArrayQuad.setIndexBuffer(indexBufferQuad);
            vertexArrayQuad.setVertexBuffer(bufferQuad, new BufferLayout(
                    new BufferLayout.BufferEntry("a_Position", ShaderDataType.Float3),
                    new BufferLayout.BufferEntry("a_Color", ShaderDataType.Float4),
                    new BufferLayout.BufferEntry("a_TexCoord", ShaderDataType.Float2)
            ));


            var camera = new OrthographicCamera();
            camera.setZRotation((float) Math.toRadians(25));

            shadersQuad.bind();
            shadersQuad.uploadUniformMatrix4("u_transform", camera.getMatrix());
            shadersQuad.uploadUniform("u_Texture", 0);
            while (!window.isClose()) {
                Platform.getRenderCommand().clear();


                float ratio = (float) window.getWidth() / window.getHeight();
                var view = Mat.ortho(ratio, -ratio, -1, 1);
                shadersQuad.uploadUniformMatrix4("u_viewProjection", view);


                vertexArrayQuad.bind();
                shadersQuad.bind();
                texture.bind(0);
//                shadersQuad.uploadUniform("u_Color", Vec.make_vec4(1, 0, 1, 1));
                Platform.getRenderCommand().drawElements(vertexArrayQuad, indexBufferQuad.count());
                window.swap();
                Platform.processEvent();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
