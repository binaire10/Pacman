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

/**
 * Moteur physique gérant les collisions
 */
@EngineLayer
public class PhysicLayer implements Layer {

    private static PhysicLayer engine;
    private final List<CollideListener> collideListeners = new ArrayList<>();/**< Liste des entités physique existante à un moment T */
    private Map<Sprite, PhysicEntity> physicEntities; /**< Map regroupant les entités physique lié à leur sprite(entité graphique le représentant */

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

    /**
     * A chaque mise à jour de l'écran,nous parcourons toutes nos entites physique et vérifions si il y a collision entre elle si oui alors
     * nous sommes notifié de cette collision par notifyCollide
     * @param timestep
     */
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

    /**
     * Ajoute une entité physique avec un sprite à un Layer passé en parametre
     * @param source
     * @param sprite Entité graphique
     * @return
     */
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
