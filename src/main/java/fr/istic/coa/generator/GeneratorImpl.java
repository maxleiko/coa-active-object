package fr.istic.coa.generator;

import fr.istic.coa.observer.AsyncObserver;
import fr.istic.coa.observer.Observer;
import fr.istic.coa.strategy.BroadcastAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorImpl implements Generator {

	private Random random = new Random();
	private Value value = new Value();
	private BroadcastAlgo broadcast;
	private List<AsyncObserver<Generator>> observers = new ArrayList<>();

	public void generate() {
		// create a new random value
		final int value = this.random.nextInt(100);
		System.out.println("New value: " + value);
		this.value.setValue(value);
		this.notifyAsyncObservers();
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public void setBroadcast(BroadcastAlgo broadcast) {
		System.out.println(" > " + broadcast.getClass().getSimpleName());
		this.broadcast = broadcast;
	}

	@Override
	public BroadcastAlgo getBroadcast() {
		return this.broadcast;
	}

	@Override
	public void attach(AsyncObserver<Generator> o) {
		this.observers.add(o);
	}

	@Override
	public void detach(AsyncObserver<Generator> o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyAsyncObservers() {
		try {
			this.broadcast.configure(this);
			this.broadcast.execute();
		} catch (InterruptedException e) {
			throw new RuntimeException("Something went wrong while calling update() on observers", e);
		}
	}

	@Override
	public List<AsyncObserver<Generator>> getAsyncObservers() {
		return this.observers;
	}
}
