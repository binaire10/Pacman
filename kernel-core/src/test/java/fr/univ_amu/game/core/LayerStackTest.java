package fr.univ_amu.game.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LayerStackTest {
    @Test
    public void test_push_layer() {
        LayerStack layers = new LayerStack();
        Layer A = mock(Layer.class);
        Layer B = mock(Layer.class);
        layers.pushLayer(A, B);
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
        layers.pushLayer(A, B);
        layers.pushOverlay(C);
        layers.pushLayer(D);
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
    public void test_clear() {
        LayerStack layers = new LayerStack();
        Layer A = mock(Layer.class);
        Layer B = mock(Layer.class);
        Layer C = mock(Layer.class);
        Layer D = mock(Layer.class);

        int[] test = new int[1];

        doAnswer(i -> {
            test[0] = test[0] * 4;
            return null;
        }).when(A).onDetach();
        doAnswer(i -> {
            test[0] = test[0] + 3;
            return null;
        }).when(B).onDetach();
        doAnswer(i -> {
            test[0] = test[0] * 2;
            return null;
        }).when(D).onDetach();
        doAnswer(i -> {
            test[0] = 1;
            return null;
        }).when(C).onDetach();

        layers.pushLayer(A, B);
        layers.pushOverlay(C);
        layers.pushLayer(D);

        verify(A, times(1)).onAttach();
        verify(B, times(1)).onAttach();
        verify(C, times(1)).onAttach();
        verify(D, times(1)).onAttach();

        verify(A, times(0)).onDetach();
        verify(B, times(0)).onDetach();
        verify(C, times(0)).onDetach();
        verify(D, times(0)).onDetach();

        layers.clear();
        verify(A, times(1)).onDetach();
        verify(B, times(1)).onDetach();
        verify(C, times(1)).onDetach();
        verify(D, times(1)).onDetach();

        assertEquals(20, test[0]);
    }

    @Test
    public void test_remove_layer() {
        LayerStack layers = new LayerStack();
        Layer A = mock(Layer.class);
        Layer B = mock(Layer.class);
        layers.pushLayer(A, B);
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
        layers.pushLayer(A, B);
        layers.pushOverlay(C);
        layers.pushLayer(D);
        layers.popLayer(C);
        layers.pushOverlay(E, F);
        layers.popOverlay(C);
        layers.popOverlay(D);
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