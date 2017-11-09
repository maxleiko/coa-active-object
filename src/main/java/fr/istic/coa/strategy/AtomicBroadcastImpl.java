package fr.istic.coa.strategy;

import fr.istic.coa.generator.Generator;
import fr.istic.coa.observer.CompletableObserverImpl;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
		CompletableFuture.allOf(
				this.generator.getObservers()
						.stream()
						.map(CompletableObserverImpl::new)
						.map((o) -> o.update(this.generator))
						.collect(Collectors.toList())
						.toArray(new CompletableFuture[this.generator.getObservers().size()])
		);
	}
}
