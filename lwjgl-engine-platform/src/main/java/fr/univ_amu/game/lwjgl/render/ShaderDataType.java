package fr.univ_amu.game.lwjgl.render;

public enum ShaderDataType {
    Float(4, 1),
    Float2(4, 2),
    Float3(4, 3),
    Float4(4, 4),
    Float2x2(4, 2 * 2),
    Float3x3(4, 3 * 3),
    Float4x4(4, 4 * 4),
    Int(4, 1),
    Int2(4, 2),
    Int3(4, 3),
    Int4(4, 4),
    Bool(1, 1);

    private final int length;
    private final int componentCount;

    ShaderDataType(int length, int componentCount) {
        this.length = length;
        this.componentCount = componentCount;
    }

    public int getSize() {
        return length * componentCount;
    }

    public int getLength() {
        return length;
    }

    public int getComponentCount() {
        return componentCount;
    }
}
