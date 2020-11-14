package fr.univ_amu.game.physics;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.loader.EngineLayer;
import fr.univ_amu.game.event.Event;

import java.util.ArrayList;
import java.util.List;

@EngineLayer
public class PhysicLayer implements Layer {

    private List<PhysicEntity> physicEntities;
    static PhysicLayer engine;

    public static PhysicLayer getEngine() {
        return engine;
    }

    @Override
    public void onAttach() {
        physicEntities = new ArrayList<>();
        engine = this;
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onUpdate(double timestep) {
        for(PhysicEntity physicEntity : physicEntities) {
            physicEntity.update(timestep);
        }
    }

    @Override
    public void onEvent(Event e) {

    }

    public void addPhysicEntity(PhysicEntity physicEntity) {
        physicEntities.add(physicEntity);
    }
}
