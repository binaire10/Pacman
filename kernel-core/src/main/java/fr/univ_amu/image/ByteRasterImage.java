package fr.univ_amu.image;

import java.util.Objects;

public class ByteRasterImage extends DimensionRasterImage {
    private final byte[] pixels;

    public ByteRasterImage(int width, int height, byte[] pixels) {
        super(width, height);
        this.pixels = pixels;
    }

    @Override
    public Image subImage(int x, int y, int width, int height) {
        Objects.checkFromIndexSize(x, width, this.getWidth());
        Objects.checkFromIndexSize(y, height, this.getHeight());
        byte[] pixels = new byte[width * height];
        for (int i = 0; i < height; i++)
            System.arraycopy(this.pixels, (y + i) * getWidth() + x, pixels, y * width, width);
        return new ByteRasterImage(width, height, pixels);
    }

    @Override
    public byte[] toPixels() {
        return pixels;
    }
}
