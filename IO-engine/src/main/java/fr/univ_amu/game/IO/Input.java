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
/**
 * Classe gérant les inputs de maniere générique
 */
public class Input implements  Layer {

    private static Input instance; /**< Instance est utilisé pour pouvoir fournir cette classe au autre moteur sans créer de nouvel objet */

    private HashMap<KeyCode,Boolean> Keys;/**< Hashmap liant les keycodes present dans @class KeyCode représentant des touches à des boolean donnant
      leur état True pour pressé et False pour non pressé */

    public static Input getInstance() {
        return instance;
    }

    /**
     * Simple getter retournant la hashmap des touches pressé
     * @return
     */
    public HashMap<KeyCode, Boolean> getKeys() {
        return Keys;
    }

    /**
     * initialise la Hashmap
     */
    @Override
    public void onAttach() {
        Keys = new HashMap<>();
        for(KeyCode key : KeyCode.values()){
            Keys.put(key,false);
        }
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

    /**
     * Lorsque un évenement clavier est detecté,la hashmap est mise à jour en fonction du type d'evenement (presser/relacher)
     * et de la touche à l'origine de cet evenement.
     * @param e
     */
    @Override
    public void onEvent(Event e) {
        Utility.dispatch(e, KeyPressedEvent.class, k->{
            Keys.put(k.keyCode,true);
        });
        Utility.dispatch(e, KeyReleasedEvent.class,k->{
            Keys.put(k.keyCode,false);
        });

    }
}
