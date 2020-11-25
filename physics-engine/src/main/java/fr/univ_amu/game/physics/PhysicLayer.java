package fr.univ_amu.game.physics;

import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.core.loader.EngineLayer;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.math.Rectangle2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EngineLayer
public class PhysicLayer implements Layer {

    private static PhysicLayer engine;
    private final List<CollideListener> collideListeners = new ArrayList<>();
    private Map<Sprite, PhysicEntity> physicEntities;

    public static PhysicLayer getEngine() {
        return engine;
    }

    @Override
    public void onAttach() {
        physicEntities = new HashMap<>();
        engine = this;
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onUpdate(double timestep) {
        List<PhysicEntity> entities = new ArrayList<>(physicEntities.values());
        List<Rectangle2D> oldShape = entities.stream().map(PhysicEntity::getShape).collect(Collectors.toList());
        for (PhysicEntity physicEntity : entities) {
            physicEntity.update(timestep);
        }
        for (int i = 0; i < entities.size(); i++) {
            PhysicEntity a = entities.get(i);
            Rectangle2D oldA = oldShape.get(i);
            for (int j = i + 1; j < entities.size(); j++) {
                PhysicEntity b = entities.get(j);
                if (a.getShape().isIntersect(b.getShape())) {
                    Rectangle2D oldB = oldShape.get(j);
                    notifyCollide(a, oldA, b, oldB);
                }
            }
        }
    }

    @Override
    public void onEvent(Event e) {

    }

    public PhysicEntity add(Class<? extends Layer> source, Sprite sprite) {
        PhysicMoveEntity entity = new PhysicMoveEntity(source, sprite);
        physicEntities.put(sprite, entity);
        return entity;
    }

    public void remove(Sprite sprite) {
        physicEntities.remove(sprite);
    }

    public void addCollideListener(CollideListener collideListener) {
        this.collideListeners.add(collideListener);
    }

    public void removeCollideListener(CollideListener collideListener) {
        this.collideListeners.remove(collideListener);
    }

    private void notifyCollide(PhysicEntity obj1, Rectangle2D oldObj1, PhysicEntity obj2, Rectangle2D oldObj2) {
        for (CollideListener collideListener : collideListeners) {
            collideListener.collideBetween(obj1, oldObj1, obj2, oldObj2);
        }
    }
}
