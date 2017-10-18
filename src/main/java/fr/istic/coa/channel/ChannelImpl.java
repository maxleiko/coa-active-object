package fr.istic.coa.channel;

import fr.istic.coa.generator.AsyncGenerator;
import fr.istic.coa.generator.Generator;
import fr.istic.coa.generator.Value;
import fr.istic.coa.methodinvocation.GetValue;
import fr.istic.coa.methodinvocation.Update;
import fr.istic.coa.observer.AsyncObserver;
import fr.istic.coa.observer.Observer;
import fr.istic.coa.scheduler.Scheduler;
import fr.istic.coa.strategy.BroadcastAlgo;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * Canal de diffusion (proxy de Generator dans ActiveObject)
 *
 */
public class ChannelImpl implements Channel {

	private String name;
	private int latency;
	private Generator generator;
	private Scheduler scheduler = Scheduler.getInstance();
	private Observer<AsyncGenerator> observer;

	public ChannelImpl(String name, Generator generator, int latency) {
		this.name = name;
		this.generator = generator;
		this.latency = latency;
	}

	@Override
	public Value getValue() {
		return this.generator.getValue();
	}

	@Override
	public void setBroadcast(BroadcastAlgo broadcast) {
		this.generator.setBroadcast(broadcast);
	}

	@Override
	public BroadcastAlgo getBroadcast() {
		return this.generator.getBroadcast();
	}

	@Override
	public void attach(Observer<AsyncGenerator> o) {
		this.observer = o;
	}

	@Override
	public void detach(Observer<AsyncGenerator> o) {
		this.observer = null;
	}

	@Override
	public void notifyObservers() {}

	@Override
	public List<Observer<AsyncGenerator>> getObservers() {
		return Collections.singletonList(this.observer);
	}

	/**
	 * Called when a new value is available in the generator subject
	 * @param subject instance qui a notifié l'observateur
	 */
	@Override
	public Future<Void> update(Generator subject) {
		System.out.println("Register call to Update() for '"+this.name+"' in " + this.latency + "ms");
		return this.scheduler.schedule(new Update(this.observer, this), this.latency);
	}

	@Override
	public Future<Value> asyncGetValue() {
		System.out.println("Register call to GetValue() for '"+this.name+"' in " + this.latency + "ms");
		return this.scheduler.schedule(new GetValue(this.generator), this.latency);
	}


	@Override
	public void attach(AsyncObserver<Generator> o) {
		this.generator.attach(o);
	}

	@Override
	public void detach(AsyncObserver<Generator> o) {
		this.generator.detach(o);
	}

	@Override
	public void notifyAsyncObservers() {
		this.generator.notifyAsyncObservers();
	}

	@Override
	public List<AsyncObserver<Generator>> getAsyncObservers() {
		return this.generator.getAsyncObservers();
	}
}
