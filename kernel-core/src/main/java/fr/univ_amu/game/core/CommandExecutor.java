package fr.univ_amu.game.core;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.FutureTask;
import java.util.function.ToIntFunction;

public class CommandExecutor {
    private final ToIntFunction<Queue<FutureTask<?>>> pendingCount;
    private final Queue<FutureTask<?>> pending = new ConcurrentLinkedQueue<>();
    private final boolean close = false;

    public CommandExecutor() {
        pendingCount = (q) -> (q.size() + 1) / 2;
    }

    public CommandExecutor(ToIntFunction<Queue<FutureTask<?>>> pendingCount) {
        this.pendingCount = pendingCount;
    }

    public void cancel() {
        pending.forEach(p -> p.cancel(true));
    }

    public void add(FutureTask<?> task) {
        if (close) task.cancel(true);
        else pending.add(task);
    }

    public void compute() {
        int count = pendingCount.applyAsInt(pending);
        for (int i = 0; i < count; i++) {
            FutureTask<?> futureTask = pending.poll();
            Objects.requireNonNull(futureTask);
            try {
                futureTask.run();
            } catch (Throwable throwable) {
                futureTask.cancel(true);
                throw throwable;
            }
        }
    }
}
