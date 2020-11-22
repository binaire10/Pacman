import fr.univ_amu.game.IO.Input;
import fr.univ_amu.game.core.Layer;

module IO.engine {
    requires kernel.core;
    exports fr.univ_amu.game.IO;
    provides Layer with Input;
}