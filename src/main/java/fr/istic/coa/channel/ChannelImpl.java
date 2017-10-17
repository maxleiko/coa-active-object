package fr.istic.coa.channel;

import fr.istic.coa.generator.Generator;
import fr.istic.coa.generator.Value;
import fr.istic.coa.methodinvocation.GetValue;
import fr.istic.coa.observer.Observer;
import fr.istic.coa.scheduler.Scheduler;
import fr.istic.coa.strategy.BroadcastAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * Canal de diffusion (proxy de Generator dans ActiveObject)
 *
 */
public class ChannelImpl implements Channel {

	private int latency;
	private Generator generator;
	private Scheduler scheduler = Scheduler.getInstance();
	private List<Observer<Generator>> observers = new ArrayList<>();

	public ChannelImpl(Generator generator, int latency) {
		this.generator = generator;
		this.latency = latency;
	}

	@Override
	public Value getValue() {
		try {
			return this.asyncGetValue();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("Something went wrong while executing value()", e);
		}
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
	public void attach(Observer<Generator> o) {
		this.observers.add(o);
	}

	@Override
	public void detach(Observer<Generator> o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		this.observers.forEach((o) -> o.update(this));
	}

	@Override
	public List<Observer<Generator>> getObservers() {
		return this.observers;
	}

	/**
	 * Called when a new value is available in the generator subject
	 * @param subject instance qui a notifié l'observateur
	 */
	@Override
	public void update(Generator subject) {
		this.notifyObservers();
	}

	private Value asyncGetValue() throws InterruptedException, ExecutionException {
		return this.scheduler.schedule(new GetValue(this.generator), this.latency).get();
	}
}
