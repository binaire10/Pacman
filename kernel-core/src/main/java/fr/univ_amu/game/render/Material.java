package fr.univ_amu.game.render;

import java.util.Map;
import java.util.TreeMap;

public interface Material extends Bindable {
    static Map<ShaderType, String> splitCode(String txt) {
        final String token = "#type";
        int pos = txt.indexOf(token);
        Map<ShaderType, String> code = new TreeMap<>();
        while (pos != -1) {
            int eol = txt.indexOf('\n', pos);
            String type = txt.substring(pos + token.length() + 1, eol);

            pos = txt.indexOf(token, eol);
            if (pos == -1)
                code.put(ShaderType.shaderTypeFromString(type), txt.substring(eol + 1));
            else
                code.put(ShaderType.shaderTypeFromString(type), txt.substring(eol + 1, pos));
        }
        return code;
    }

    void uploadUniform(String key, float value);

    void uploadUniform(String key, float[] vector);

    void uploadUniformMatrix3(String key, float[] matrix);

    void uploadUniformMatrix4(String key, float[] matrix);
}
