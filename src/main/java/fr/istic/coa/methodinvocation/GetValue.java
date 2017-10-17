package fr.istic.coa.methodinvocation;

import fr.istic.coa.generator.Generator;
import fr.istic.coa.generator.Value;

import java.util.concurrent.Callable;

public class GetValue implements Callable<Value> {

	private Generator generator;

	public GetValue(Generator generator) {
		this.generator = generator;
	}

	@Override
	public Value call() throws Exception {
		return generator.getValue();
	}
}
