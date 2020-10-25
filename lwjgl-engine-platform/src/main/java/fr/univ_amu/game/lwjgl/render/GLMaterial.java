package fr.univ_amu.game.lwjgl.render;

import fr.univ_amu.game.render.Material;
import fr.univ_amu.game.render.ShaderType;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL33.*;

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
                int maxLength = glGetShaderi(id, GL_INFO_LOG_LENGTH);
                String message = glGetShaderInfoLog(id, maxLength);
                for (int i = 0; i < index; ++i)
                    glDeleteShader(shaders[i]);
                throw new RuntimeException(message);
            }
        }
        program = glCreateProgram();
        for (int i = 0; i < index; ++i)
            glAttachShader(program, shaders[i]);
        glLinkProgram(program);
        int isLinked = glGetProgrami(program, GL_LINK_STATUS);

        if (isLinked == GL_FALSE) {
            int maxLength = glGetProgrami(program, GL_INFO_LOG_LENGTH);
            String message = glGetProgramInfoLog(program, maxLength);
            for (int i = 0; i < index; ++i) {
                glDetachShader(program, shaders[i]);
                glDeleteShader(shaders[i]);
            }
            glDeleteProgram(program);
            throw new RuntimeException(message);
        }

        for (int i = 0; i < index; ++i) {
            glDetachShader(program, shaders[i]);
            glDeleteShader(shaders[i]);
        }

        int nbUniforms = glGetProgrami(program, GL_ACTIVE_UNIFORMS);
        int maxUniformLen = glGetProgrami(program, GL_ACTIVE_UNIFORM_MAX_LENGTH);

        try (var stackMem = MemoryStack.stackPush()) {
            var length = stackMem.mallocInt(1);
            var size = stackMem.mallocInt(1);
            var type = stackMem.mallocInt(1);
            var name = stackMem.malloc(maxUniformLen);
            for (int i = 0; i < nbUniforms; ++i) {
                glGetActiveUniform(program, i, length, size, type, name);
                String str = MemoryUtil.memASCII(name, length.get(0));
                System.out.println(str + " " + i + " " + length.get(0));
                uniforms.put(str, glGetUniformLocation(program, str));
                if (str.contains("[")) {
                    str = str.substring(0, str.indexOf('['));
                    uniforms.put(str, glGetUniformLocation(program, str));
                }
            }
        }
        System.out.println(uniforms);
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

    @Override
    public void close() throws IOException {
        System.out.println("free program");
        glDeleteProgram(program);
    }
}
