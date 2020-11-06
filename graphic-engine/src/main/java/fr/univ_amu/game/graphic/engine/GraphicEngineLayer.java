package fr.univ_amu.game.graphic.engine;

import fr.univ_amu.game.core.MainLayer;
import fr.univ_amu.game.core.Platform;
import fr.univ_amu.game.core.loader.EngineLayer;
import fr.univ_amu.game.event.Event;

@EngineLayer
public class GraphicEngineLayer implements MainLayer {
    static GraphicEngine engine;
    AutoCloseable thread;

    public static GraphicEngine getEngine() {
        return engine;
    }

    @Override
    public void onUpdate(double timestep) {

    }

    @Override
    public void onEvent(Event e) {
        engine.postEvent(e);
    }

    @Override
    public void onAttach() {
        thread = Platform.startGraphicEngine(engine = new GraphicEngine());
    }

    @Override
    public void onDetach() {
        engine.shutdown();
        try {
            thread.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
