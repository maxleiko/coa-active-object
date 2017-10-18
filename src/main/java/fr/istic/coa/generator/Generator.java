package fr.istic.coa.generator;

import fr.istic.coa.observer.AsyncSubject;
import fr.istic.coa.observer.Subject;
import fr.istic.coa.strategy.BroadcastAlgo;

/**
 *
 *         Interface de capteur
 * 
 */
public interface Generator extends AsyncSubject<Generator> {

	/**
	 * Retourne la valeur courante du capteur
	 * 
	 * @return la valeur courante
	 */
	Value getValue();

	/**
	 * Assigne une strategie de diffusion
	 * 
	 * @param broadcast
	 */
	void setBroadcast(BroadcastAlgo broadcast);

	/**
	 *
	 * @return la stratégie de diffusion
	 */
	BroadcastAlgo getBroadcast();
}
