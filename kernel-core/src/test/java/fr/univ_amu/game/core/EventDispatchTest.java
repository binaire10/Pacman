package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.WindowEvent;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.event.application.WindowResizeEvent;
import fr.univ_amu.game.event.keyboard.KeyEvent;
import fr.univ_amu.game.event.keyboard.KeyPressedEvent;
import fr.univ_amu.game.event.keyboard.KeyReleasedEvent;
import fr.univ_amu.game.event.keyboard.KeyTypedEvent;
import fr.univ_amu.game.event.mouse.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventDispatchTest {
    @Test
    public void test_dispatch_event() {
        Event[] tests = new Event[]{
                new WindowCloseEvent(null),
                new WindowResizeEvent(5, 5, null),
                new KeyPressedEvent(null, true, null),
                new KeyReleasedEvent(null, null),
                new KeyTypedEvent('0', null),
                new MouseButtonPressedEvent(null, null),
                new MouseButtonReleasedEvent(null, null),
                new MouseMovedEvent(1, 1, null),
                new MouseScrolledEvent(1, 1, null)
        };
        boolean[] check_call = new boolean[tests.length];
        int[] counter = new int[3];
        for (int i = 0; i < tests.length; i++) {
            EventDispatch dispatch = new EventDispatch(tests[i]);
            int finalI = i;
            dispatch.dispatch(WindowEvent.class, f -> {
                ++counter[0];
                check_call[finalI] = true;
            });
            dispatch.dispatch(KeyEvent.class, f -> {
                ++counter[1];
                check_call[finalI] = true;
            });
            dispatch.dispatch(MouseButtonEvent.class, f -> {
                ++counter[2];
                check_call[finalI] = true;
            });
        }
        assertEquals(2, counter[2]);
        assertEquals(2, counter[1]);
        assertEquals(9, counter[0]);
        boolean[] true_array = new boolean[check_call.length];
        Arrays.fill(true_array, true);
        assertArrayEquals(check_call, true_array);
    }
}