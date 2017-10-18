package fr.istic.coa.channel;

import fr.istic.coa.generator.AsyncGenerator;
import fr.istic.coa.generator.Generator;
import fr.istic.coa.observer.AsyncObserver;
import fr.istic.coa.observer.Subject;


/**
 *
 * Interface de canal de diffusion
 *
 */
public interface Channel extends AsyncGenerator, Generator, AsyncObserver<Generator>, Subject<AsyncGenerator> {

}
