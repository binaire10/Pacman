package fr.univ_amu.game.graphic.entities;

import fr.univ_amu.game.beans.Binding;
import fr.univ_amu.game.beans.ChangeValueListener;
import fr.univ_amu.game.beans.ObjectProperty;
import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.core.Sprite;
import fr.univ_amu.game.graphic.engine.GraphicEngine;
import fr.univ_amu.game.math.Point2D;
import fr.univ_amu.game.math.Point3D;
import fr.univ_amu.game.math.Rectangle2D;
import fr.univ_amu.game.render.Texture2D;

import java.util.Arrays;

/**
 * Classe gérant nos primitive graphique, sous forme rectangulaire sur laquelle nous pouvons appliqué des textures tel que des images ou des couleurs
 */

public class QuadEntity {
    private final Sprite sprite;
    private final ObjectProperty<float[]> color;
    private final ObjectProperty<Texture2D> texture;
    private final ObjectProperty<Point3D> position;
    private final ObjectProperty<Point2D> size;

    private final ChangeValueListener<Texture2D> textureListener;
    private final ChangeValueListener<float[]> colorListener;
    private final ChangeValueListener<Rectangle2D> sizeListener;
    private final ChangeValueListener<Point2D> positionListener;
    /**
     * Constructeur créant une entité graphique,sous forme rectangulaire
     * @param source Couche intermédiare possedant des sprites aidant la synchronisation si multi threading utilisé
     * @param sprite Image/Couleur associé au rectangle
     * @param z Profondeur de l'affichage(comprise entre -1 et 1) -1 étant le plus proche de l'utilisateur
     * @return
     */
    public QuadEntity(Class<? extends Layer> source, Sprite sprite, float z) {
        this.sprite = sprite;
        this.color = new ObjectProperty<>();
        this.texture = new ObjectProperty<>();
        this.size = new ObjectProperty<>();
        this.position = new ObjectProperty<>();

        this.textureListener = Binding.bind(source, sprite.getTextureProperty(), GraphicEngine.class, texture);
        this.colorListener = Binding.bind(source, sprite.getColorProperty(), GraphicEngine.class, color);
        this.positionListener = Binding.bind(source, sprite.getPositionProperty(), GraphicEngine.class, position, p -> new Point3D(p, z));
        this.sizeListener = Binding.bind(source, sprite.getShapeProperty(), GraphicEngine.class, size, s -> new Point2D(s.width, s.heigth));
    }

    /**
     * Constructeur créant une entité graphique à un point donnée,d'une taille donnée avec la texture de notre choix
     * @param position
     * @param size
     * @param color
     * @param texture
     */
    public QuadEntity(Point3D position, Point2D size, float[] color, Texture2D texture) {
        this.sprite = null;
        this.color = new ObjectProperty<>(color);
        this.texture = new ObjectProperty<>(texture);
        this.size = new ObjectProperty<>(size);
        this.position = new ObjectProperty<>(position);

        this.textureListener = null;
        this.colorListener = null;
        this.positionListener = null;
        this.sizeListener = null;
    }

    public QuadEntity(Point3D position, Point2D size, Texture2D texture) {
        this(position, size, null, texture);
    }

    public QuadEntity(Point3D position, Point2D size, float[] color) {
        this(position, size, color, null);
    }

    public float[] getColor() {
        return color.getValue();
    }

    public void setColor(float[] color) {
        this.color.setValue(Arrays.copyOf(color, color.length));
    }

    public Texture2D getTexture() {
        return texture.getValue();
    }

    public void setTexture(Texture2D texture) {
        this.texture.setValue(texture);
    }

    public Point3D getPosition() {
        return position.getValue();
    }

    public void setPosition(Point3D position) {
        this.position.setValue(position);
    }

    public Point2D getSize() {
        return size.getValue();
    }

    public ObjectProperty<Point3D> getPositionProperty() {
        return position;
    }

    public void unbind() {
        sprite.getTextureProperty().removeListener(textureListener);
        sprite.getColorProperty().removeListener(colorListener);
        sprite.getPositionProperty().removeListener(positionListener);
        sprite.getShapeProperty().removeListener(sizeListener);
    }
}
