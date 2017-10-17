package fr.istic.coa.observer;

import java.util.concurrent.Future;

/**
 *
 * Interface d'observeur (Pattern Observer)
 *
 */
public interface AsyncObserver<T> {

	/**
	 *
	 * @param subject instance qui a notifié l'observateur
	 */
	Future<Void> update(T subject);
}
