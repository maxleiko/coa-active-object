package fr.istic.coa.strategy;

import fr.istic.coa.generator.Generator;
import fr.istic.coa.methodinvocation.Update;
import fr.istic.coa.scheduler.Scheduler;

/**
 *
 *         Diffusion par époque
 * 
 */
public class VersionBroadcastImpl implements BroadcastAlgo {

	private Scheduler scheduler = Scheduler.getInstance();
	private Generator generator;

	@Override
	public void configure(Generator generator) {
		this.generator = generator;
	}

	@Override
	public void execute() {
		// TODO
		this.generator.getObservers()
				.forEach((o) -> this.scheduler.submit(new Update(o, this.generator)));
	}
}
