package fr.istic.coa.channel;

import fr.istic.coa.generator.AsyncGenerator;
import fr.istic.coa.generator.Generator;
import fr.istic.coa.generator.Value;
import fr.istic.coa.methodinvocation.GetValue;
import fr.istic.coa.methodinvocation.Update;
import fr.istic.coa.observer.Observer;
import fr.istic.coa.scheduler.Scheduler;

import java.util.Collections;
import java.util.List;
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
	public void attach(Observer<AsyncGenerator> o) {
		this.observer = o;
	}

	@Override
	public void detach(Observer<AsyncGenerator> o) {
		this.observer = null;
	}

	@Override
	public void notifyObservers() {
		this.observer.update(this);
	}

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
		System.out.println(" "+this.name+" scheduled update() in " + this.latency + "ms");
		return this.scheduler.schedule(new Update(this), this.latency);
	}

	@Override
	public Future<Value> getValue() {
		System.out.println(" "+this.name+" scheduled getValue() in " + this.latency + "ms");
		return this.scheduler.schedule(new GetValue(this.generator), this.latency);
	}
}
