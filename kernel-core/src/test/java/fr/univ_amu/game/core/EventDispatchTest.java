package fr.univ_amu.game.core;

import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.WindowEvent;
import fr.univ_amu.game.event.application.WindowCloseEvent;
import fr.univ_amu.game.event.application.WindowResizeEvent;
import fr.univ_amu.game.util.Utility;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class EventDispatchTest {
    @Test
    public void test_dispatch_event() {
        Event[] tests = new Event[]{
                new WindowCloseEvent(null),
                new WindowResizeEvent(5, 5, null)
        };
        boolean[] check_call = new boolean[tests.length];
        for (int i = 0; i < tests.length; i++) {
            int finalI = i;
            Utility.dispatch(tests[i], WindowEvent.class, f -> {
                check_call[finalI] = true;
            });
        }
        boolean[] true_array = new boolean[check_call.length];
        Arrays.fill(true_array, true);
        assertArrayEquals(check_call, true_array);
    }
}