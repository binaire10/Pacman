package fr.univ_amu.image;

public interface Image {
    int getWidth();

    int getHeight();

    Image subImage(int x, int y, int width, int height);

    byte[] toPixels();
}
