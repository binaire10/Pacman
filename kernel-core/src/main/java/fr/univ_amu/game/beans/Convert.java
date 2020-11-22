package fr.univ_amu.game.beans;

import fr.univ_amu.game.math.VectorUtility;

import java.util.function.Function;

public final class Convert {
    public static final Function<float[], float[]> TO_VEC2 = VectorUtility::make_vec2;
    public static final Function<float[], float[]> TO_VEC3 = VectorUtility::make_vec3;
    public static final Function<float[], float[]> TO_VEC4 = VectorUtility::make_vec4;

    public static Function<float[], float[]> toVector2D() {
        return TO_VEC2;
    }

    public static Function<float[], float[]> toVector3D() {
        return TO_VEC3;
    }

    public static Function<float[], float[]> toVector4D() {
        return TO_VEC4;
    }
}
