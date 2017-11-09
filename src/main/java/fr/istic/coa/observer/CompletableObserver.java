package fr.istic.coa.observer;

import java.util.concurrent.CompletableFuture;

/**
 *
 * Interface d'observeur (Pattern Observer)
 *
 */
public interface CompletableObserver<T> {

	/**
	 *
	 * @param subject instance qui a notifié l'observateur
	 */
	CompletableFuture<Void> update(T subject);
}
