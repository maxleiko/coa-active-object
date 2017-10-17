package fr.istic.coa.strategy;

import fr.istic.coa.generator.Generator;


/**
 *
 * Interface de stratégie de diffusion (Pattern Strategy)
 *
 */
public interface BroadcastAlgo {

	/**
	 * Configure la stratégie
	 * @param generator
	 */
	void configure(Generator generator);
	
	/**
	 * Déclenche la diffusion
	 */
	void execute() throws InterruptedException;
}
