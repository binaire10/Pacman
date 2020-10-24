package fr.univ_amu.game;

import fr.univ_amu.game.render.Material;
import fr.univ_amu.game.render.ShaderType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MaterialTest {
    private static final String FRAGMENT_SRC = "#version 330 core\n" +
                    "\n" +
                    "layout(location = 0) out vec4 color;\n" +
                    "\n" +
                    "uniform vec4 u_Color;\n" +
                    "\n" +
                    "void main()\n" +
                    "{\n" +
                    "    color = u_Color;\n" +
                    "}\n";
    private static final String VERTEX_SRC = "#version 330 core\n"+
                    "\n"+
                    "layout(location = 0) in vec3 a_Position;\n"+
                    "\n"+
                    "uniform mat4 u_viewProjection;\n"+
                    "uniform mat4 u_transform;\n"+
                    "\n"+
                    "void main()\n"+
                    "{\n"+
                    "    gl_Position = u_viewProjection * u_transform * vec4(a_Position, 1.0);\n"+
                    "}\n";
    private static final String CODE_SRC = "#type vertex\n" +
            VERTEX_SRC +
            "#type fragment\n" +
            FRAGMENT_SRC;

    @Test
    void test_shader_split_reader() {
        Map<ShaderType, String> shader = Material.splitCode(CODE_SRC);
        assertEquals(2, shader.size());
        assertEquals(FRAGMENT_SRC, shader.get(ShaderType.FRAGMENT));
        assertEquals(VERTEX_SRC, shader.get(ShaderType.VERTEX));
    }
}
