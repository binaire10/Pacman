package fr.univ_amu.game.lwjgl.render;

import fr.univ_amu.game.render.Texture2D;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UnknownFormatConversionException;

import static org.lwjgl.opengl.GL33.*;

public class GLTexture2D implements Texture2D {
    private final int id;
    private final int width;
    private final int height;

    public GLTexture2D(ByteBuffer data) {
        ByteBuffer storage = data.isDirect() ? data : BufferUtils.createByteBuffer(data.capacity()).put(data).flip();
        try (var memory = MemoryStack.stackPush()) {
            var x = memory.mallocInt(1);
            var y = memory.mallocInt(1);
            var channel = memory.mallocInt(1);
            STBImage.stbi_set_flip_vertically_on_load(true);
            var pixels = STBImage.stbi_load_from_memory(storage, x, y, channel, 0);
            int internalFormat;
            int dataFormat;
            switch (channel.get(0)) {
                case 4:
                    internalFormat = GL_RGBA8;
                    dataFormat = GL_RGBA;
                    break;
                case 3:
                    internalFormat = GL_RGB8;
                    dataFormat = GL_RGB;
                    break;
                case 2:
                    internalFormat = GL_RG8;
                    dataFormat = GL_RG;
                    break;
                default:
                    throw new UnknownFormatConversionException("channel require = " + channel.get(0));
            }
            id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);
            glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width = x.get(0), height = y.get(0), 0, dataFormat, GL_UNSIGNED_BYTE, pixels);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        }
    }

    public GLTexture2D(int w, int h) {
        width = w;
        height = h;
        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, 0);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    }

    @Override
    public void bind(int textureUnit) {
        glActiveTexture(GL_TEXTURE0 + textureUnit);
        glBindTexture(GL_TEXTURE_2D, id);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setPixels(ByteBuffer pixels) {
        ByteBuffer storage = pixels.isDirect() ? pixels : BufferUtils.createByteBuffer(pixels.capacity()).put(pixels).flip();
        glTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, width, height, GL_RGBA, GL_UNSIGNED_BYTE, storage);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Release Texture");
        glDeleteTextures(id);
    }
}
