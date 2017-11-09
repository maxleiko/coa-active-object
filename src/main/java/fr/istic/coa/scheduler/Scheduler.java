package fr.istic.coa.scheduler;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * Created by leiko on 10/17/17.
 */
public class Scheduler {

    private static Scheduler INSTANCE;
    private ScheduledExecutorService dispatcher;

    private Scheduler() {}

    public static Scheduler getInstance() {
        if (Scheduler.INSTANCE == null) {
            Scheduler.INSTANCE = new Scheduler();
        }
        return Scheduler.INSTANCE;
    }

    public void start() {
        if (this.dispatcher == null) {
            this.dispatcher = Executors.newScheduledThreadPool(Integer.MAX_VALUE);
        }
    }

    public void stop() {
        if (this.dispatcher != null) {
            this.dispatcher.shutdown();
            this.dispatcher = null;
        }
    }

    public <V> Future<V> schedule(Callable<V> callable, long delay) {
        return this.dispatcher.schedule(callable, delay, TimeUnit.MILLISECONDS);
    }

    public Future<?> submit(Runnable runnable) {
        return this.dispatcher.submit(runnable);
    }

    public void schedulePeriodically(Runnable task, long delay, long period) {
        this.dispatcher.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
    }
}
