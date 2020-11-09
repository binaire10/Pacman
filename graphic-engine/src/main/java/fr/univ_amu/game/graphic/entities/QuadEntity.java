package fr.univ_amu.game.graphic.entities;

import fr.univ_amu.game.render.Texture2D;

import java.util.Arrays;

public class QuadEntity {
    float[] color;
    Texture2D texture;
    float[] position;
    float[] size;
    float rotation;

    public QuadEntity(float[] position, float[] size, float[] color, Texture2D texture, float rotation) {
        this.color = color;
        this.texture = texture;
        this.size = size;
        this.position = position;
        this.rotation = rotation;
    }

    public QuadEntity(float[] position, float[] size, float rotation, Texture2D texture) {
        this(position, size, null, texture, rotation);
    }

    public QuadEntity(float[] position, float[] size, float rotation, float[] color) {
        this(position, size, color, null, rotation);
    }

    public QuadEntity(float[] position, float[] size, float[] color, Texture2D texture) {
        this(position, size, color, texture, 0);
    }

    public QuadEntity(float[] position, float[] size, Texture2D texture) {
        this(position, size, null, texture, 0);
    }

    public QuadEntity(float[] position, float[] size, float[] color) {
        this(position, size, color, null, 0);
    }

    public float[] getColor() {
        return color;
    }

    public void setColor(float[] color) {
        this.color = Arrays.copyOf(color, color.length);
    }

    public Texture2D getTexture() {
        return texture;
    }

    public void setTexture(Texture2D texture) {
        this.texture = texture;
    }

    public float[] getPosition() {
        return position;
    }

    public void setPosition(float[] position) {
        this.color = Arrays.copyOf(position, position.length);
    }

    public float[] getSize() {
        return size;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
