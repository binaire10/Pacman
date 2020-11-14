import fr.univ_amu.game.core.Layer;
import fr.univ_amu.game.physics.PhysicLayer;

module physics.engine {
    requires kernel.core;
    exports fr.univ_amu.game.physics;
    provides Layer with PhysicLayer;
}