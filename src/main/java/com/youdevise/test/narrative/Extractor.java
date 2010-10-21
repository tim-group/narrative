package com.youdevise.test.narrative;

/**
 * Extraction of data to check the state of the system during a Then statement.
 * @param <T> The type of data that is going to be fetched.
 * @param <F> The type of the tool to use for fetching the data.
 */
public interface Extractor<T, ACTOR extends Actor<?>> {
    T grabFor(ACTOR actor);
}
