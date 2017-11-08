package fr.istic.coa.methodinvocation;

import fr.istic.coa.observer.Subject;

import java.util.concurrent.Callable;

public class Update implements Callable<Void> {
	
	private Subject subject;

	public Update(Subject subject) {
		this.subject = subject;
	}
	
	public Void call() throws Exception {
		this.subject.notifyObservers();
		return null;
	}
}
