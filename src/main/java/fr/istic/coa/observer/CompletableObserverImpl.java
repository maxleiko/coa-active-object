package fr.istic.coa.observer;

import fr.istic.coa.scheduler.Scheduler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * Created by leiko on 11/9/17.
 */
public class CompletableObserverImpl<T> implements CompletableObserver<T> {

    private AsyncObserver<T> adaptee;
    private Scheduler scheduler = Scheduler.getInstance();

    public CompletableObserverImpl(AsyncObserver<T> observer) {
        this.adaptee = observer;
    }

    @Override
    public CompletableFuture<Void> update(T subject) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        this.scheduler.submit(() -> {
            try {
                this.adaptee.update(subject).get();
                future.complete(null);
            } catch (InterruptedException | ExecutionException e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }
}
