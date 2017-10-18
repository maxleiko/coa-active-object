package fr.istic.coa.methodinvocation;

import fr.istic.coa.generator.AsyncGenerator;
import fr.istic.coa.observer.Observer;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {
	
	private Observer<AsyncGenerator> observer;
	private AsyncGenerator subject;


	public Update(Observer<AsyncGenerator> observer, AsyncGenerator subject) {
		this.observer = observer;
		this.subject = subject;
	}
	
	public Void call() throws Exception {
		observer.update(this.subject);
		return null;
	}
}
