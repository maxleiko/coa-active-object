package fr.istic.coa.strategy;

import fr.istic.coa.generator.Generator;
import fr.istic.coa.observer.AsyncObserver;

import java.util.concurrent.ExecutionException;

/**
 *
 *         Diffusion atomique: Tous les observateurs recoivent la même valeur
 * 		   => on bloque pour chaque update()
 */
public class AtomicBroadcastImpl implements BroadcastAlgo {

	private Generator generator;

	@Override
	public void configure(Generator generator) {
		this.generator = generator;
	}

	@Override
	public void execute() {
		// block for each update()
		for (AsyncObserver<Generator> observer : this.generator.getAsyncObservers()) {
			try {
				observer.update(this.generator).get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException("Something went wrong while calling update()", e);
			}
		}
	}
}
