package fr.univ_amu.game.lwjgl.render;

public class BufferLayout {
    private int stride;
    private final BufferEntry[] entries;

    public BufferLayout(BufferEntry... entries) {
        int index = 0;
        stride = 0;
        for (BufferEntry entry : entries) {
            entry.index = index++;
            entry.offset = stride;
            stride += entry.type.getSize();
        }
        this.entries = entries;
    }

    public BufferEntry[] getEntries() {
        return entries;
    }

    public int getStride() {
        return stride;
    }

    public static class BufferEntry {
        public final String name;
        public final ShaderDataType type;
        public final boolean normalized;
        int offset;
        int index;

        public BufferEntry(String name, ShaderDataType type) {
            this(name, type, false);
        }

        public BufferEntry(String name, ShaderDataType type, boolean normalized) {
            this.name = name;
            this.type = type;
            this.normalized = normalized;
        }

        public int getOffset() {
            return offset;
        }

        public int getIndex() {
            return index;
        }
    }
}
