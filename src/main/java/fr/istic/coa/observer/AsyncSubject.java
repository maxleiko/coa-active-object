package fr.istic.coa.observer;

import java.util.List;


/**
 *
 * Interface de Subject (Pattern Observer)
 *
 */
public interface AsyncSubject<T> {

	/**
	 * Attache un observer au sujet
	 * @param o l'observer à attacher
	 */
	void attach(AsyncObserver<T> o);
	
	/**
	 * Détache un observer du sujet
	 * @param o l'observer à détacher
	 */
	void detach(AsyncObserver<T> o);
	
	/**
	 * Envoie une notification aux observers
	 */
	void notifyObservers();

	/**
	 * @return la liste des observeurs enregistrés
	 */
	List<AsyncObserver<T>> getObservers();
}
