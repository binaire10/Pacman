package fr.univ_amu.game.pacman;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.Window;
import fr.univ_amu.game.math.Mat;
import fr.univ_amu.game.render.*;

import fr.univ_amu.game.graphic.camera.OrthographicCamera;
import fr.univ_amu.game.graphic.render2D.BatchRender2D;
import fr.univ_amu.game.math.Vec;
import fr.univ_amu.game.render.Material;
import fr.univ_amu.game.render.Texture2D;

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

        var camera = new OrthographicCamera((float) window.getWidth() / window.getHeight());
        try (
                Texture2D texture = Platform.load_texture(read(Main.class.getResource("Checkerboard.png")));
                BatchRender2D render2D = new BatchRender2D()
        ) {
            while (!window.isClose()) {
                Platform.getRenderCommand().clear();
                camera.setRatio((float) window.getWidth() / window.getHeight());

                render2D.begin(camera);
                for (int i = 0; i < 33; i++) {
                    for (int j = 0; j < 33; j++) {
                        render2D.drawQuad(Vec.make_vec4((i - 16f) * 0.1f, (j - 16f) * 0.1f, 0), Vec.make_vec2(0.09f, 0.09f), Vec.make_vec4(1, 0, 1, 1));
                    }
                }
                render2D.drawQuad(Vec.make_vec4(0, 0, 0.1f), Vec.make_vec2(4, 4), texture, 5);
                render2D.end();

                window.swap();
                Platform.processEvent();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
