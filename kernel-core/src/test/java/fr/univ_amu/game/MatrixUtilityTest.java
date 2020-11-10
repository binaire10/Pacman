package fr.univ_amu.game;

import fr.univ_amu.game.math.MatrixUtility;
import fr.univ_amu.game.math.VectorUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MatrixUtilityTest {
    @Test
    public void test_matrix_translate() {
        float[] translate1 = MatrixUtility.translateMatrix4(MatrixUtility.identity(4), VectorUtility.make_vec4(3, 2, 1));
        assertArrayEquals(new float[]{3, 2, 1, 1}, VectorUtility.dot_product(new float[]{0, 0, 0, 1}, translate1, 4), 1e-5f);
    }

    @Test
    public void test_rotation() {
        float[] rotate1 = MatrixUtility.rotateMatrix4(MatrixUtility.identity(4), new float[]{0, 0, 1}, (float) Math.toRadians(90));
        assertArrayEquals(new float[]{0, 1, 0, 1}, VectorUtility.dot_product(new float[]{1, 0, 0, 1}, rotate1, 4), 1e-5f);
    }
}