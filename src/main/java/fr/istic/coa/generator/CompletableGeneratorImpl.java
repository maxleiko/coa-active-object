package fr.istic.coa.generator;

import fr.istic.coa.scheduler.Scheduler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * Created by leiko on 11/9/17.
 */
public class CompletableGeneratorImpl implements CompletableGenerator {

    private AsyncGenerator adaptee;
    private Scheduler scheduler = Scheduler.getInstance();

    public CompletableGeneratorImpl(AsyncGenerator generator) {
        this.adaptee = generator;
    }

    @Override
    public CompletableFuture<Value> getValue() {
        CompletableFuture<Value> future = new CompletableFuture<>();
        scheduler.submit(() -> {
            Value value;
            try {
                value = this.adaptee.getValue().get();
                future.complete(value);
            } catch (InterruptedException | ExecutionException e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }
}
