package fr.univ_amu.game.render;

public enum ShaderType {
    VERTEX,
    FRAGMENT;

    static ShaderType shaderTypeFromString(String type) {
        if (type.equals("vertex"))
            return VERTEX;
        if (type.equals("fragment") || type.equals("pixel"))
            return FRAGMENT;
        throw new IllegalArgumentException(type);
    }
}
