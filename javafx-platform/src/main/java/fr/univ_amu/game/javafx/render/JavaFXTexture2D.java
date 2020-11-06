package fr.univ_amu.game.javafx.render;

import fr.univ_amu.game.render.Texture2D;
import fr.univ_amu.game.util.Utility;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.ByteBuffer;

public class JavaFXTexture2D implements Texture2D {
    private int width, height;
    private Image image;

    public JavaFXTexture2D(ByteBuffer data) {
        image = new Image(Utility.asInputStream(data));
    }

    public JavaFXTexture2D(int width, int height) {
        this.width = width;
        this.height = height;
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
        byte[] pixel = new byte[4];
        var image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();
        int x = 0, y = 0;
        while (pixels.hasRemaining()) {
            pixels.get(pixel);
            pixelWriter.setColor(x++, y, Color.color((double) pixel[0] / 255, (double) pixel[1] / 255, (double) pixel[2] / 255, (double) pixel[3] / 255));
            if (x == width) {
                ++y;
                x = 0;
            }
        }
        this.image = image;
    }

    @Override
    public void close() throws IOException {

    }
}
