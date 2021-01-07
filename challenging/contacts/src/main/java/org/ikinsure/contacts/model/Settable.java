package org.ikinsure.contacts.model;

import java.io.Serializable;
import java.util.Scanner;

/**
 * must implement method for setting class values
 */
@FunctionalInterface
public interface Settable extends Serializable {
    void setValue(Scanner scanner);
}
