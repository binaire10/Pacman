package fr.univ_amu.game;

import fr.univ_amu.game.math.Mat;
import fr.univ_amu.game.math.Vec;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MatTest {
    @Test
    public void test_matrix_translate() {
        float[] translate1 = Mat.translateMatrix4(Mat.identity(4), Vec.make_vec4(3, 2, 1));
        assertArrayEquals(new float[]{3, 2, 1, 1}, Vec.dot_product(new float[]{0, 0, 0, 1}, translate1, 4), 1e-5f);
    }

    @Test
    public void test_rotation() {
        float[] rotate1 = Mat.rotateMatrix4(Mat.identity(4), new float[]{0, 0, 1}, (float) Math.toRadians(90));
        assertArrayEquals(new float[]{0, 1, 0, 1}, Vec.dot_product(new float[]{1, 0, 0, 1}, rotate1, 4), 1e-5f);
    }
}