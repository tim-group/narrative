package com.youdevise.test.narrative;

/**
 * Extraction of data to check the state of the system during a Then statement.
 * @param <DATA> The type of data that is going to be fetched.
 * @param <ACTOR> The type of the actor that is fetching data
 */
public interface Extractor<DATA, ACTOR extends Actor<?>> {
    DATA grabFor(ACTOR actor);
}
