package fr.istic.coa.strategy;

import fr.istic.coa.generator.Generator;

/**
 *
 *         Diffusion séquentielle
 *         => Peut importe qu'on perde des ticks dans le combat
 * 
 */
public class SequentialBroadcastImpl implements BroadcastAlgo {

	private Generator generator;

	@Override
	public void configure(Generator generator) {
		this.generator = generator;
	}

	@Override
	public void execute() {
		this.generator.getObservers().forEach(o -> o.update(this.generator));
	}
}
