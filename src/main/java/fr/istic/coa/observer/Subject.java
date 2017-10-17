package fr.istic.coa.observer;

import java.util.List;


/**
 *
 * Interface de Subject (Pattern Observer)
 *
 */
public interface Subject<T> {

	/**
	 * Attache un observer au sujet
	 * @param o l'observer à attacher
	 */
	void attach(Observer<T> o);
	
	/**
	 * Détache un observer du sujet
	 * @param o l'observer à détacher
	 */
	void detach(Observer<T> o);
	
	/**
	 * Envoie une notification aux observers
	 */
	void notifyObservers();

	/**
	 * @return la liste des observeurs enregistrés
	 */
	List<Observer<T>> getObservers();
}
