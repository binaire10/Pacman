package fr.univ_amu.game.lwjgl.render;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BufferLayoutTest {
    @Test
    public void test_buffer_layout() {
        BufferLayout bufferLayout = new BufferLayout(
                new BufferLayout.BufferEntry("a_float", ShaderDataType.Float),
                new BufferLayout.BufferEntry("a_float2", ShaderDataType.Float2),
                new BufferLayout.BufferEntry("a_float3", ShaderDataType.Float3),
                new BufferLayout.BufferEntry("a_float4", ShaderDataType.Float4),
                new BufferLayout.BufferEntry("a_float4x4", ShaderDataType.Float4x4),
                new BufferLayout.BufferEntry("a_float3x3", ShaderDataType.Float3x3),
                new BufferLayout.BufferEntry("a_int", ShaderDataType.Int),
                new BufferLayout.BufferEntry("a_int2", ShaderDataType.Int2),
                new BufferLayout.BufferEntry("a_int3", ShaderDataType.Int3),
                new BufferLayout.BufferEntry("a_int4", ShaderDataType.Int4),
                new BufferLayout.BufferEntry("a_show", ShaderDataType.Bool)
        );
        int offset = 0;
        for (var entry : bufferLayout.getEntries()) {
            assertEquals(offset, entry.getOffset());
            offset += entry.type.getSize();
        }
        assertEquals(offset, bufferLayout.getStride());
    }
}