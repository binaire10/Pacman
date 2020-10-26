package fr.univ_amu.game.graphic.render2D;

import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.graphic.camera.Camera;
import fr.univ_amu.game.math.Vec;
import fr.univ_amu.game.render.*;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.stream.IntStream;

public class BatchRender2D implements Closeable {
    public static final int QUAD_COUNT = 500;
    public static final int VERTEX_PER_QUAD = 4;
    public static final BufferLayout QUAD_LAYOUT = new BufferLayout(
            new BufferLayout.BufferEntry("a_Position", ShaderDataType.Float3),
            new BufferLayout.BufferEntry("a_Color", ShaderDataType.Float4),
            new BufferLayout.BufferEntry("a_TexCoord", ShaderDataType.Float2),
            new BufferLayout.BufferEntry("a_TexIndex", ShaderDataType.Float),
            new BufferLayout.BufferEntry("a_TilingFactor", ShaderDataType.Float)
    );
    private static final float[][] quadVertices = {
            {-0.5f, -0.5f, 0.f, 1.f},
            {0.5f, -0.5f, 0.f, 1.f},
            {0.5f, 0.5f, 0.f, 1.f},
            {-0.5f, 0.5f, 0.f, 1.f}
    };
    private static final float[][] quadTextCoord = {
            {0, 0},
            {1, 0},
            {1, 1},
            {0, 1}
    };
    private final FloatBuffer buffer = ByteBuffer.allocateDirect(QUAD_LAYOUT.getStride() * VERTEX_PER_QUAD * QUAD_COUNT).order(ByteOrder.nativeOrder()).asFloatBuffer();
    private final VertexArray vertexArray;
    private final IndexBuffer indexBuffer;
    private final VertexBuffer vertexBuffer;
    private final Texture2D white;
    private final Material shader;
    private final Texture2D[] textures = new Texture2D[32];
    private int textureOffsets = 0;
    private int quadCount = 0;

    public BatchRender2D() throws IOException {
        System.out.println(QUAD_LAYOUT.getStride());
        shader = Platform.create_material(Material.splitCode(new String(BatchRender2D.class.getResourceAsStream("batchShader.glsl").readAllBytes())));
        shader.uploadUniform("u_Textures", IntStream.range(0, 32).toArray());

        int[] indices = new int[QUAD_COUNT * 6];
        int offset = 0;
        for (int i = 0; i < indices.length; i += 6) {
            indices[i] = offset;
            indices[i + 1] = offset + 1;
            indices[i + 2] = offset + 2;

            indices[i + 3] = offset + 2;
            indices[i + 4] = offset + 3;
            indices[i + 5] = offset;
            offset += 4;
        }
        indexBuffer = Platform.make_index(indices);

        vertexBuffer = Platform.make_buffer(QUAD_COUNT * VERTEX_PER_QUAD * QUAD_LAYOUT.getStride());

        vertexArray = Platform.create_vertexArray();
        vertexArray.setIndexBuffer(indexBuffer);
        vertexArray.setVertexBuffer(vertexBuffer, QUAD_LAYOUT);

        white = Platform.make_texture(1, 1);
        white.setPixels(ByteBuffer.wrap(new byte[]{(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}));
    }

    public void begin(Camera camera) {
        shader.bind();
        shader.uploadUniformMatrix4("u_ViewProjection", camera.getMatrix());
    }


    public void drawQuad(float[] position, float[] size, float[] color) {
        drawQuad(position, size, color, white, 1);
    }

    public void drawQuad(float[] position, float[] size, float[] color, Texture2D texture2D, float tillingFactor) {
        float[] matrix = {
                size[0], 0, 0, position[0],
                0, size[1], 0, position[1],
                0, 0, 1, position[2],
                0, 0, 0, 1
        };
        drawQuadWithTransform(matrix, color, texture2D, tillingFactor);
    }

    public void end() {
        flush();
    }

    public void drawQuadWithTransform(float[] matrix, float[] color, Texture2D texture2D, float tillingFactor) {
        int textureIndex;
        for (textureIndex = 0; textureIndex < textureOffsets; textureIndex++)
            if (texture2D.equals(textures[textureIndex]))
                break;
        if (textureIndex == textureOffsets)
            ++textureOffsets;
        if (textureOffsets == textures.length) {
            flush();
            textureIndex = textureOffsets++;
        }
        textures[textureIndex] = texture2D;
        pushQuad(matrix, color, textureIndex, tillingFactor);
    }

    private void pushQuad(float[] matrix, float[] color, float textureIndex, float tillingFactor) {
        ++quadCount;
        for (int i = 0; i < quadVertices.length; i++) {
            float[] r = Vec.dot_product(quadVertices[i], matrix, 4);
            buffer.put(r, 0, 3);
            buffer.put(color);
            buffer.put(quadTextCoord[i]);
            buffer.put(textureIndex);
            buffer.put(tillingFactor);
        }
        if (!buffer.hasRemaining())
            flush();
    }

    private void flush() {
        buffer.flip();
        vertexBuffer.write(buffer);
        shader.bind();
        vertexArray.bind();
        for (int i = 0; i < textureOffsets; i++)
            textures[i].bind(i);
        Platform.getRenderCommand().drawElements(vertexArray, quadCount * 6);
        buffer.clear();
        textureOffsets = 0;
        quadCount = 0;

    }

    @Override
    public void close() throws IOException {
        white.close();
        shader.close();
        vertexArray.close();
        indexBuffer.close();
        vertexBuffer.close();
    }
}
