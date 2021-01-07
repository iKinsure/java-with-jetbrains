package org.ikinsure.contacts.model;

import java.util.Scanner;

/**
 * must implement method for setting class values
 */
@FunctionalInterface
public interface Settable {
    void setValue(Scanner scanner);
}
