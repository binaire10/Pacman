import fr.univ_amu.game.core.KeyCode;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Input implements EventHandler<KeyEvent> {

    public HashMap<KeyCode,Boolean> Keys;

    public Input(){
        Keys = new HashMap<>();
        for(KeyCode key : KeyCode.values()){
            Keys.put(key,false);
        }
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        Keys.replace(KeyCode.valueOf(keyEvent.getCode().getName()),true);
    }

    public HashMap<KeyCode, Boolean> getKeys() {
        return Keys;
    }
}
