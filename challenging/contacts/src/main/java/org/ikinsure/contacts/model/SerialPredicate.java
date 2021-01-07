package org.ikinsure.contacts.model;

import java.io.Serializable;

/**
 * interface for serialization & testing
 */
@FunctionalInterface
public interface SerialPredicate extends Serializable {
    boolean test(String str);
}
