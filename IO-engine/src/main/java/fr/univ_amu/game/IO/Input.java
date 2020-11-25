package fr.univ_amu.game.IO;

import fr.univ_amu.game.core.KeyCode;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.loader.EngineLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.keyboard.KeyPressedEvent;
import fr.univ_amu.game.event.keyboard.KeyReleasedEvent;
import fr.univ_amu.game.util.Utility;

import java.util.HashMap;

@EngineLayer

public class Input implements  Layer {

    private static Input instance;



    public HashMap<KeyCode,Boolean> Keys;


    public static Input getInstance() {
        return instance;
    }


    public HashMap<KeyCode, Boolean> getKeys() {
        return Keys;
    }

    @Override
    public void onAttach() {
        Keys = new HashMap<>();
        for(KeyCode key : KeyCode.values()){
            Keys.put(key,false);
        }
        System.out.println("Chargement input?");
        instance = this;
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void beforeUpdate() {

    }

    @Override
    public void onUpdate(double timestep) {

    }

    @Override
    public void afterUpdate() {

    }

    @Override
    public void onEvent(Event e) {
        Utility.dispatch(e, KeyPressedEvent.class, k->{
            Keys.put(k.keyCode,true);
            System.out.println(k.keyCode);
        });
        Utility.dispatch(e, KeyReleasedEvent.class,k->{
            Keys.put(k.keyCode,false);
        });

    }
}
