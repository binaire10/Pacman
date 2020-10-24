package fr.univ_amu.game.lwjgl.render;

import fr.univ_amu.game.render.Material;
import fr.univ_amu.game.render.ShaderType;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL30.*;

public class GLMaterial implements Material {
    private final int program;
    private final Map<String, Integer> uniforms = new HashMap<>();

    public GLMaterial(Map<ShaderType, String> sources) {
        int[] shaders = new int[sources.size()];
        int index = 0;
        for (Map.Entry<ShaderType, String> source : sources.entrySet()) {
            int shader = index++;
            int id = glCreateShader(getShaderType(source.getKey()));
            shaders[shader] = id;
            glShaderSource(id, source.getValue());
            glCompileShader(id);
            int isCompiles = glGetShaderi(id, GL_COMPILE_STATUS);
            if (isCompiles == GL_FALSE) {
                int[] maxLength = {0};
                glGetShaderiv(id, GL_INFO_LOG_LENGTH, maxLength);
                byte[] msg = new byte[maxLength[0]];
                glGetShaderInfoLog(id, maxLength, ByteBuffer.wrap(msg));
                for (int i = 0; i < index; ++i)
                    glDeleteShader(shaders[i]);
                throw new RuntimeException(new String(msg));
            }
        }
        program = glCreateProgram();
        for (int i = 0; i < index; ++i)
            glAttachShader(program, shaders[i]);
        glLinkProgram(program);
        int isLinked = glGetProgrami(program, GL_LINK_STATUS);

        if (isLinked == GL_FALSE) {
            int[] maxLength = {0};
            glGetProgramiv(program, GL_INFO_LOG_LENGTH, maxLength);
            byte[] msg = new byte[maxLength[0]];
            glGetProgramInfoLog(program, maxLength, ByteBuffer.wrap(msg));
            for (int i = 0; i < index; ++i) {
                glDetachShader(program, shaders[i]);
                glDeleteShader(shaders[i]);
            }
            glDeleteProgram(program);
            throw new RuntimeException(new String(msg));
        }

        for (int i = 0; i < index; ++i) {
            glDetachShader(program, shaders[i]);
            glDeleteShader(shaders[i]);
        }

        int nbUniforms = glGetProgrami(program, GL_ACTIVE_ATTRIBUTES);
        int[] maxUniformLen = {0};
        glGetProgramiv(program, GL_ACTIVE_ATTRIBUTE_MAX_LENGTH, maxUniformLen);
        int[] type = {};
        for (int i = 0; i < nbUniforms; ++i) {
            byte[] buffer = new byte[maxUniformLen[0]];
            glGetActiveAttrib(program, i, IntBuffer.wrap(maxUniformLen), IntBuffer.allocate(1), IntBuffer.wrap(type), ByteBuffer.wrap(buffer));
            String name = new String(buffer);
            uniforms.put(name, glGetAttribLocation(program, name));
            if (name.contains("[")) {
                name = name.substring(0, name.indexOf('['));
                uniforms.put(name, glGetAttribLocation(program, name));
            }
        }
    }

    private static int getShaderType(ShaderType shaderType) {
        return switch (shaderType) {
            case VERTEX -> GL_VERTEX_SHADER;
            case FRAGMENT -> GL_FRAGMENT_SHADER;
        };
    }

    @Override
    public void uploadUniform(String key, float value) {
        glUniform1f(uniforms.get(key), value);
    }

    @Override
    public void uploadUniform(String key, float[] vector) {
        switch (vector.length) {
            case 1:
                glUniform1fv(uniforms.get(key), vector);
                break;
            case 2:
                glUniform2fv(uniforms.get(key), vector);
                break;
            case 3:
                glUniform3fv(uniforms.get(key), vector);
                break;
            case 4:
                glUniform4fv(uniforms.get(key), vector);
                break;
            default:
                throw new IllegalArgumentException("uniform vector too long");
        }
    }

    @Override
    public void uploadUniformMatrix3(String key, float[] matrix) {
        glUniformMatrix3fv(uniforms.get(key), false, matrix);
    }

    @Override
    public void uploadUniformMatrix4(String key, float[] matrix) {
        glUniformMatrix4fv(uniforms.get(key), false, matrix);
    }

    @Override
    public void bind() {
        glUseProgram(program);
    }
}
