package fr.istic.coa.strategy;

import fr.istic.coa.generator.Generator;

/**
 *
 *         Diffusion par époque
 * 
 */
public class VersionBroadcastImpl implements BroadcastAlgo {

	private Generator generator;

	@Override
	public void configure(Generator generator) {
		this.generator = generator;
	}

	@Override
	public void execute() {
		// TODO
		this.generator.getObservers().forEach(o -> o.update(this.generator));
	}
}
