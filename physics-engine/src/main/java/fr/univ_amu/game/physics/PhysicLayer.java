package fr.univ_amu.game.physics;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.loader.EngineLayer;
import fr.univ_amu.game.event.Event;

import java.util.ArrayList;
import java.util.List;

@EngineLayer
public class PhysicLayer implements Layer {

    private List<PhysicEntity> physicEntities;
    private List<CollideListener> collideListeners = new ArrayList<>();
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
        for(int i = 0; i < physicEntities.size(); i++) {
            PhysicEntity a = physicEntities.get(i);
            for (int j = i+1; j < physicEntities.size(); j++) {
                PhysicEntity b = physicEntities.get(j);
                if(a.getShape().isCollided(b.getShape())) {
                    notifyCollide(a, b);
                }
            }
        }
    }

    @Override
    public void onEvent(Event e) {

    }

    public void addPhysicEntity(PhysicEntity physicEntity) {
        physicEntities.add(physicEntity);
    }

    public void addCollideListener(CollideListener collideListener) {
        this.collideListeners.add(collideListener);
    }

    public void removeCollideListener(CollideListener collideListener) {
        this.collideListeners.remove(collideListener);
    }

    private void notifyCollide(PhysicEntity obj1, PhysicEntity obj2) {
        for (CollideListener collideListener : collideListeners) {
            collideListener.collideBetween(obj1, obj2);
        }
    }
}
