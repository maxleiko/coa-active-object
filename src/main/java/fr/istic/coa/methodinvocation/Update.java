package fr.istic.coa.methodinvocation;

import fr.istic.coa.generator.Generator;
import fr.istic.coa.observer.Observer;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {
	
	private Observer<Generator> observer;
	private Generator subject;


	public Update(Observer<Generator> observer, Generator subject) {
		this.observer = observer;
		this.subject = subject;
	}
	
	public Void call() throws Exception {
		observer.update(this.subject);
		return null;
	}
}
