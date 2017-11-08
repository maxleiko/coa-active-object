package fr.istic.coa.generator;

import java.util.concurrent.Future;

/**
 *
 * Created by leiko on 10/18/17.
 */
public interface AsyncGenerator {

    Future<Value> getValue();
}
