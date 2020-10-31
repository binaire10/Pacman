package fr.univ_amu.game.core.loader;

import fr.univ_amu.game.core.Layer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLayer {
    Class<? extends Layer>[] require();
}
