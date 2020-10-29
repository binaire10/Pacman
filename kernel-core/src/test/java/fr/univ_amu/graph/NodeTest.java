package fr.univ_amu.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    @Test
    void test_set_value() {
        Node<Integer> value = new Node<>(5);
        assertEquals(5, value.getValue());
        assertTrue(value.getChildren().isEmpty());
        value.setValue(6);
        assertEquals(6, value.getValue());
        assertTrue(value.getChildren().isEmpty());
        value.add(value);
        assertEquals(6, value.getValue());
        assertFalse(value.getChildren().isEmpty());
    }

}