package fr.univ_amu.game.render;

import java.io.Closeable;
import java.util.Map;
import java.util.TreeMap;

public interface Material extends Bindable, Closeable {
    static Map<ShaderType, String> splitCode(String txt) {
        final String token = "#type";
        int pos = txt.indexOf(token);
        Map<ShaderType, String> code = new TreeMap<>();
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

    void uploadUniform(String key, int value);

    void uploadUniform(String key, int[] value);

    void uploadUniform(String key, float value);

    void uploadUniform(String key, float[] vector);

    void uploadUniformMatrix3(String key, float[] matrix);

    void uploadUniformMatrix4(String key, float[] matrix);
}

