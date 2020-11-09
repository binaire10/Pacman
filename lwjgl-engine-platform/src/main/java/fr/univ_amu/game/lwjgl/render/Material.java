package fr.univ_amu.game.lwjgl.render;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL33.*;

public final class Material implements Bindable {
    private final int program;
    private final Map<String, Integer> uniforms = new HashMap<>();

    public Material(Map<ShaderType, String> sources) {
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

    public static Map<ShaderType, String> splitCode(String txt) {
        final String token = "#type";
        int pos = txt.indexOf(token);
        Map<ShaderType, String> code = new HashMap<>();
        while (pos != -1) {
            int eol1 = txt.indexOf('\n', pos);
            int eol2 = txt.indexOf('\r', pos);
            int eol = eol2 == -1 ? eol1 : eol1 == -1 ? eol2 : Math.min(eol1, eol2);
            String type = txt.substring(pos + token.length() + 1, eol);

            pos = txt.indexOf(token, eol);
            if (pos == -1)
                code.put(ShaderType.shaderTypeFromString(type), txt.substring(eol + 1));
            else
                code.put(ShaderType.shaderTypeFromString(type), txt.substring(eol + 1, pos));
        }
        return code;
    }

    private static int getShaderType(ShaderType shaderType) {
        return switch (shaderType) {
            case VERTEX -> GL_VERTEX_SHADER;
            case FRAGMENT -> GL_FRAGMENT_SHADER;
        };
    }

    public void uploadUniform(String key, int value) {
        glUniform1i(uniforms.get(key), value);
    }

    public void uploadUniform(String key, int[] value) {
        glUniform1iv(uniforms.get(key), value);
    }

    public void uploadUniform(String key, float value) {
        glUniform1f(uniforms.get(key), value);
    }

    public void uploadUniform(String key, float[] vector) {
        glUniform1fv(uniforms.get(key), vector);
    }

    public void uploadUniformMatrix3(String key, float[] matrix) {
        glUniformMatrix3fv(uniforms.get(key), false, matrix);
    }

    public void uploadUniformMatrix4(String key, float[] matrix) {
        glUniformMatrix4fv(uniforms.get(key), false, matrix);
    }

    @Override
    public void bind() {
        glUseProgram(program);
    }

    public void close() {
        System.out.println("free program");
        glDeleteProgram(program);
    }
}
