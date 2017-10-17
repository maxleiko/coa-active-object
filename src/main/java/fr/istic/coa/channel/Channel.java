package fr.istic.coa.channel;

import fr.istic.coa.generator.Generator;
import fr.istic.coa.observer.Observer;


/**
 *
 * Interface de canal de diffusion
 *
 */
public interface Channel extends Generator, Observer<Generator> {
}
