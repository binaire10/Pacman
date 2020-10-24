package fr.univ_amu.game.math;

public final class Mat {
    public static float[] identity(int n) {
        float[] res = new float[n * n];
        for (int i = 0; i < n; i++)
            res[i + i * n] = 1;
        return res;
    }

    public static float[] ortho(float left, float right, float bottom, float top, float zNear, float zFar) {
        return new float[] {
                2f/(right - left)           , 0                         , 0                         , 0,
                0                           , 2f/(top - bottom)         , 0                         , 0,
                0                           , 0                         , -2f/(zFar - zNear)        , 0,
                -(right+left)/(right-left)  , -(top+bottom)/(top-bottom), -(zFar+zNear)/(zFar-zNear), 1
        };
    }

    public static float[] dot_product(float[] A, float[] B, int n) {
        float[] result = new float[n * n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    result[i + j * n] += A[i + k * n] * B[k + j * n];
        return result;
    }

    public static float[] pow(float[] A, int exp, int n) {
        float[] R = identity(n);
        while (exp != 0) {
            if ((exp & 1) == 1)
                R = dot_product(R, A, n);
            exp /= 2;
            A = dot_product(A, A, n);
        }
        return R;
    }
}
