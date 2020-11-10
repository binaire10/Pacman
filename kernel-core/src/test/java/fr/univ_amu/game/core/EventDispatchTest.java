package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.WindowEvent;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.event.application.WindowResizeEvent;
import fr.univ_amu.game.event.mouse.*;
import fr.univ_amu.game.util.Utility;
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
                new MouseButtonPressedEvent(null, null),
                new MouseButtonReleasedEvent(null, null),
                new MouseMovedEvent(1, 1, null),
                new MouseScrolledEvent(1, 1, null)
        };
        boolean[] check_call = new boolean[tests.length];
        int[] counter = new int[2];
        for (int i = 0; i < tests.length; i++) {
            int finalI = i;
            Utility.dispatch(tests[i], WindowEvent.class, f -> {
                ++counter[0];
                check_call[finalI] = true;
            });
            Utility.dispatch(tests[i], MouseButtonEvent.class, f -> {
                ++counter[1];
                check_call[finalI] = true;
            });
        }
        assertEquals(2, counter[1]);
        assertEquals(6, counter[0]);
        boolean[] true_array = new boolean[check_call.length];
        Arrays.fill(true_array, true);
        assertArrayEquals(check_call, true_array);
    }
}