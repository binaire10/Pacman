package fr.univ_amu.game.graphic.engine;

import fr.univ_amu.game.core.*;
import fr.univ_amu.game.event.Event;
import fr.univ_amu.game.event.application.WindowResizeEvent;
import fr.univ_amu.game.graphic.entities.GraphicEntity;
import fr.univ_amu.game.render.RenderCommand;
import fr.univ_amu.game.render.Texture2D;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class GraphicEngine implements Engine {
    private final Window window = Platform.create_window("Graphic Engine", 720, 400);
    private final LayerStack<GraphicLayer> layers = new LayerStack<>();
    private final List<GraphicEntity> graphicEntities = new CopyOnWriteArrayList<>();
    private final Queue<Event> events = new ConcurrentLinkedQueue<>();
    private final CommandExecutor executor = new CommandExecutor();
    private volatile boolean finish = false;

    @Override
    public LayerStack<GraphicLayer> getStack() {
        return layers;
    }

    @Override
    public void initialize() {
        window.make_context();
        layers.pushLayer(Platform.load_layers(ServiceLoader.load(GraphicLayer.class)).toArray(GraphicLayer[]::new));
        window.show();
    }

    @Override
    public void one_step() {
        final RenderCommand command = Platform.getRenderCommand();
        computeEvent(command);
        executor.compute();
        computeRender();
        window.swap();
    }

    @Override
    public void shutdown() {
        finish = true;
    }

    @Override
    public boolean isStop() {
        return finish;
    }

    @Override
    public void release() {
        executor.compute();
        layers.clear();
        events.clear();
    }

    private void computeRender() {
        layers.iterator().forEachRemaining(g -> g.onBegin(window));
        layers.iterator().forEachRemaining(g -> graphicEntities.forEach(g::onRender));
        layers.reverseIterator().forEachRemaining(GraphicLayer::onEnd);
    }

    private void computeEvent(RenderCommand command) {
        final int pendingEvent = (events.size() + 1) / 2;
        for (int i = 0; i < pendingEvent; i++) {
            Event e = events.poll();
            EventDispatch.dispatch(e, WindowResizeEvent.class, r -> command.setViewport(0, 0, r.width, r.height));
            layers.iterator().forEachRemaining(layer -> layer.onEvent(e));
        }
    }

    public List<GraphicEntity> getGraphicEntities() {
        return graphicEntities;
    }

    public void postEvent(Event e) {
        events.add(e);
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public Texture2D load_texture(ByteBuffer data) {
        FutureTask<Texture2D> futureTask = new FutureTask<>(() -> Platform.load_texture(data));
        executor.add(futureTask);
        try {
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Texture2D make_texture(int w, int h) {
        FutureTask<Texture2D> futureTask = new FutureTask<>(() -> Platform.make_texture(w, h));
        executor.add(futureTask);
        try {
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void free_texture(Texture2D texture2D) {
        FutureTask<Texture2D> futureTask = new FutureTask<>(() -> {
            texture2D.close();
            return null;
        });
        executor.add(futureTask);
    }
}
