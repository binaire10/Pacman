package fr.univ_amu.game.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLayer {
    Class<? extends Layer>[] require();
}
