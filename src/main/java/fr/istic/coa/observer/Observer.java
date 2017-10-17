package fr.istic.coa.observer;

/**
 *
 * Interface d'observeur (Pattern Observer)
 *
 */
public interface Observer<T> {

	/**
	 *
	 * @param subject instance qui a notifié l'observateur
	 */
	void update(T subject);
}
