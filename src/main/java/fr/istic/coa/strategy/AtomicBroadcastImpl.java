package fr.istic.coa.strategy;

import fr.istic.coa.generator.Generator;
import fr.istic.coa.methodinvocation.Update;
import fr.istic.coa.scheduler.Scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 *         Diffusion atomique: Tous les observateurs recoivent la même valeur
 * 		   => Pas d'execution d'execute() tant que toutes les value() n'ont pas été effectués
 * 		   => On ne perd pas de valeurs
 */
public class AtomicBroadcastImpl implements BroadcastAlgo {

	private Scheduler scheduler = Scheduler.getInstance();
	private Generator generator;

	@Override
	public void configure(Generator generator) {
		this.generator = generator;
	}

	@Override
	public void execute() {
		// queue update() for each observers
		BlockingQueue<Update> queue = new LinkedBlockingQueue<>();
		this.generator.getObservers()
				.forEach((o) -> queue.add(new Update(o, this.generator)));

		while (!queue.isEmpty()) {
			try {
				this.scheduler.submit(queue.take()).get();
			} catch (Exception ignore) {
				break;
			}
		}
	}
}
