package com.youdevise.test.narrative;

/**
 * Extraction of data to check the state of the system during a Then statement.
 *
 * @param <DATA>
 *            The type of data to be retrieved.
 * @param <ACTOR>
 *            The type of the actor that is retrieving the data.
 */
public interface Extractor<DATA, ACTOR extends Actor<?, ?>> {
    DATA grabFor(ACTOR actor);
}
