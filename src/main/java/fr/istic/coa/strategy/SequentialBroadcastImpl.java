package fr.istic.coa.strategy;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import fr.istic.coa.methodinvocation.Update;
import fr.istic.coa.generator.Generator;
import fr.istic.coa.scheduler.Scheduler;

/**
 *
 *         Diffusion séquentielle
 *         => Peut importe qu'on perde des ticks dans le combat
 * 
 */
public class SequentialBroadcastImpl implements BroadcastAlgo {

	private Scheduler scheduler = Scheduler.getInstance();
	private Generator generator;

	@Override
	public void configure(Generator generator) {
		this.generator = generator;
	}

	@Override
	public void execute() throws InterruptedException {
		List<Callable<Void>> tasks = this.generator.getObservers()
				.stream()
				.map((o) -> new Update(o, this.generator))
				.collect(Collectors.toList());
		this.scheduler.invokeAll(tasks);
	}
}
