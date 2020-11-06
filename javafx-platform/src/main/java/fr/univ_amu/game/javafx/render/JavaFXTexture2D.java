package fr.univ_amu.game.javafx.render;

import fr.univ_amu.game.render.Texture2D;
import fr.univ_amu.game.util.Utility;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.ByteBuffer;

public class JavaFXTexture2D implements Texture2D {
    private final Image image;

    public JavaFXTexture2D(ByteBuffer data) {
        image = new Image(Utility.asInputStream(data));
    }

    @Override
    public void bind(int textureUnit) {

    }

    @Override
    public int getWidth() {
        return (int) image.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) image.getHeight();
    }

    @Override
    public void setPixels(ByteBuffer pixels) {
    }

    @Override
    public void close() throws IOException {

    }
}
