import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class Input implements EventHandler<KeyEvent> {

    List<String> Keys = new ArrayList<>();
    @Override
    public void handle(KeyEvent keyEvent) {
        String keyPressed = keyEvent.getCode().getName();
        if(!Keys.contains(keyPressed))Keys.add(keyPressed);
    }

    public List<String> getKeys(){
        return Keys;
    }
}
