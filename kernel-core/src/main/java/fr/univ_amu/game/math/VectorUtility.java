package fr.univ_amu.game.math;

import java.util.Arrays;

public final class VectorUtility {
    public static float[] make_vec2(float x, float y) {
        return new float[]{x, y};
    }

    public static float[] make_vec3(float x, float y, float z) {
        return new float[]{x, y};
    }

    public static float[] make_vec4(float x, float y, float z) {
        return new float[]{x, y, z, 1};
    }

    public static float[] make_vec4(float x, float y, float z, float w) {
        return new float[]{x, y, z, w};
    }

    public static float[] make_vec2(float[] vec) {
        float[] result = {0, 0};
        int n = Math.min(vec.length, 2);
        for (int i = 0; i < n; ++i)
            result[i] = vec[i];
        return result;
    }

    public static float[] make_vec3(float[] vec) {
        float[] result = {0, 0, 0};
        int n = Math.min(vec.length, 3);
        for (int i = 0; i < n; ++i)
            result[i] = vec[i];
        return result;
    }

    public static float[] make_vec4(float[] vec) {
        float[] result = {0, 0, 0, 1};
        int n = Math.min(vec.length, 4);
        for (int i = 0; i < n; ++i)
            result[i] = vec[i];
        return result;
    }

    public static float[] sum(float[] A, float[] B) {
        float[] result = new float[A.length];
        for (int j = 0; j < A.length; ++j)
            result[j] = A[j] + B[j];
        return result;
    }

    public static float[] minus(float[] A, float[] B) {
        float[] result = new float[A.length];
        for (int j = 0; j < A.length; ++j)
            result[j] = A[j] - B[j];
        return result;
    }

    public static float[] negative(float[] A) {
        float[] result = new float[A.length];
        for (int j = 0; j < A.length; ++j)
            result[j] = -A[j];
        return result;
    }

    public static float[] product(float[] A, float B) {
        float[] result = new float[A.length];
        for (int j = 0; j < A.length; ++j)
            result[j] += A[j] * B;
        return result;
    }

    public static float[] product(float[] A, float[] B) {
        float[] result = new float[A.length];
        for (int j = 0; j < A.length; ++j)
            result[j] += A[j] * B[j];
        return result;
    }

    public static float dot_product(float[] A, float[] B) {
        float result = 0;
        for (int j = 0; j < A.length; ++j)
            result += A[j] * B[j];
        return result;
    }

    public static float[] dot_product(float[] vector, float[] matrix, int n) {
        int m = matrix.length / n;
        float[] result = new float[m];
        for (int j = 0; j < m; ++j)
            for (int i = 0; i < n; ++i)
                result[j] += matrix[i + j * n] * vector[i];
        return result;
    }

    public static float[] normalize(float[] vector) {
        float distance = (float) Math.sqrt(dot_product(vector, vector));
        float[] result = new float[vector.length];
        for (int i = 0; i < result.length; i++)
            result[i] = vector[i] / distance;
        return result;
    }

    public static float[] vector_fill(int n, int v) {
        float[] result = new float[n];
        Arrays.fill(result, v);
        return result;
    }
}
