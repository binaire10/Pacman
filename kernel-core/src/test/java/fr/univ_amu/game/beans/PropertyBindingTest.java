package fr.univ_amu.game.beans;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Point3D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PropertyBindingTest {
    @Test
    public void test_vector_property() {
        ObjectProperty<Point3D> a = new ObjectProperty<>(new Point3D(0, 1, 2));
        ObjectProperty<Point2D> b = new ObjectProperty<>();
        assertNull(b.getValue());
        Binding.bind(Layer.class, a, Layer.class, b, p -> new Point2D(p.x, p.y));
        assertEquals(new Point2D(0, 1), b.getValue());
        a.setValue(new Point3D(2, 1, 0));
        assertEquals(new Point2D(2, 1), b.getValue());
    }

    @Test
    public void test_vector_property_3() {
        ObjectProperty<Point3D> a = new ObjectProperty<>(new Point3D(0, 1, 2));
        ObjectProperty<Point2D> b = new ObjectProperty<>();
        ObjectProperty<Point2D> c = new ObjectProperty<>();
        assertNull(b.getValue());
        Binding.bind(Layer.class, a, Layer.class, b, p -> new Point2D(p.x, p.y));
        Binding.bind(Layer.class, a, Layer.class, c, p -> new Point2D(p.x, p.y));
        assertEquals(new Point2D(0, 1), b.getValue());
        assertEquals(new Point2D(0, 1), c.getValue());
        a.setValue(new Point3D(2, 1, 0));
        assertEquals(new Point2D(2, 1), b.getValue());
        assertEquals(new Point2D(2, 1), c.getValue());
    }
}