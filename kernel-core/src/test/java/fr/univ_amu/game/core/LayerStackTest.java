package fr.univ_amu.game.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class LayerStackTest {
    @Test
    public void test_push_layer() {
        LayerStack layers = new LayerStack();
        Layer A = mock(Layer.class);
        Layer B = mock(Layer.class);
        layers.pushLayers(A, B);
        var it = layers.iterator();
        assertTrue(it.hasNext());
        assertEquals(A, it.next());
        assertTrue(it.hasNext());
        assertEquals(B, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void test_push_overlay() {
        LayerStack layers = new LayerStack();
        Layer A = mock(Layer.class);
        Layer B = mock(Layer.class);
        Layer C = mock(Layer.class);
        Layer D = mock(Layer.class);
        layers.pushLayers(A, B);
        layers.pushOverlay(C);
        layers.pushLayers(D);
        var it = layers.iterator();
        assertTrue(it.hasNext());
        assertEquals(A, it.next());
        assertTrue(it.hasNext());
        assertEquals(B, it.next());
        assertTrue(it.hasNext());
        assertEquals(D, it.next());
        assertTrue(it.hasNext());
        assertEquals(C, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void test_remove_layer() {
        LayerStack layers = new LayerStack();
        Layer A = mock(Layer.class);
        Layer B = mock(Layer.class);
        layers.pushLayers(A, B);
        layers.popLayer(A);
        var it = layers.iterator();
        assertTrue(it.hasNext());
        assertEquals(B, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void test_remove_overlay() {
        LayerStack layers = new LayerStack();
        Layer A = mock(Layer.class);
        Layer B = mock(Layer.class);
        Layer C = mock(Layer.class);
        Layer D = mock(Layer.class);
        Layer E = mock(Layer.class);
        Layer F = mock(Layer.class);
        layers.pushLayers(A, B);
        layers.pushOverlay(C);
        layers.pushLayers(D);
        layers.popLayer(C);
        layers.pushOverlays(E, F);
        layers.popOverlays(C);
        layers.popOverlays(D);
        var it = layers.iterator();
        assertTrue(it.hasNext());
        assertEquals(A, it.next());
        assertTrue(it.hasNext());
        assertEquals(B, it.next());
        assertTrue(it.hasNext());
        assertEquals(D, it.next());
        assertTrue(it.hasNext());
        assertEquals(E, it.next());
        assertTrue(it.hasNext());
        assertEquals(F, it.next());
        assertFalse(it.hasNext());
    }
}