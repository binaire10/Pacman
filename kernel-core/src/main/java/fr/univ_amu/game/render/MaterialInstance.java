package fr.univ_amu.game.render;

import java.util.Map;
import java.util.TreeMap;

public class MaterialInstance implements Bindable {
    private final Map<String, float[]> setting = new TreeMap<>();
    private Material material;

    public void set(String name, float v) {
        setting.put(name, new float[]{v});
    }

    public void set(String name, float[] vec) {
        setting.put(name, vec);
    }

    public void bind() {
        material.bind();
        for (Map.Entry<String, float[]> entry : setting.entrySet()) {
            switch (entry.getValue().length) {
                case 1:
                    material.uploadUniform(entry.getKey(), entry.getValue()[0]);
                    break;
                case 2:
                case 3:
                case 4:
                    material.uploadUniform(entry.getKey(), entry.getValue());
                    break;
                case 3 * 3:
                    material.uploadUniformMatrix3(entry.getKey(), entry.getValue());
                    break;
                case 4 * 4:
                    material.uploadUniformMatrix4(entry.getKey(), entry.getValue());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
