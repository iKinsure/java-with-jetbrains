package org.ikinsure.contacts.model;

import java.io.Serializable;

/**
 * interface declares that class can be shown in app
 */
public interface Printable extends Serializable {
    String getProperties();
    String getPropertiesKeys();
    String getPropertiesValues();
}
