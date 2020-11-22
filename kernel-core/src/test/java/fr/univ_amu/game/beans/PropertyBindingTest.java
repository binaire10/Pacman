package fr.univ_amu.game.beans;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.math.VectorUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PropertyBindingTest {
    @Test
    public void test_vector_property() {
        ObjectProperty<float[]> a = new ObjectProperty<>(VectorUtility.make_vec4(0, 1, 2));
        ObjectProperty<float[]> b = new ObjectProperty<>();
        assertNull(b.getValue());
        Binding.bind(Layer.class, a, Layer.class, b, Convert.toVector2D());
        assertArrayEquals(new float[]{0, 1}, b.getValue());
        a.setValue(VectorUtility.make_vec4(2, 1, 0));
        assertArrayEquals(new float[]{2, 1}, b.getValue());
    }

    @Test
    public void test_vector_property_3() {
        ObjectProperty<float[]> a = new ObjectProperty<>(VectorUtility.make_vec4(0, 1, 2));
        ObjectProperty<float[]> b = new ObjectProperty<>();
        ObjectProperty<float[]> c = new ObjectProperty<>();
        assertNull(b.getValue());
        Binding.bind(Layer.class, a, Layer.class, b, Convert.toVector2D());
        Binding.bind(Layer.class, a, Layer.class, c, Convert.toVector2D());
        assertArrayEquals(new float[]{0, 1}, b.getValue());
        assertArrayEquals(new float[]{0, 1}, c.getValue());
        a.setValue(VectorUtility.make_vec4(2, 1, 0));
        assertArrayEquals(new float[]{2, 1}, b.getValue());
        assertArrayEquals(new float[]{2, 1}, c.getValue());
    }
}