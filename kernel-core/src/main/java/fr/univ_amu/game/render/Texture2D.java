package fr.univ_amu.game.render;

import java.io.Closeable;
import java.nio.ByteBuffer;

/**
 * Interface de l'une des maniere de représenté visuellement nos entité graphique
 */
public interface Texture2D extends Closeable {
    void bind(int textureUnit);

    int getWidth();

    int getHeight();

    void setPixels(ByteBuffer pixels);
}
