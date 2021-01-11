package org.ikinsure.jsondatabase.client;

import com.beust.jcommander.Parameter;

public class Task {

    @Parameter(names = "-t")
    protected String type;

    @Parameter(names = "-k")
    protected String key;

    @Parameter(names = "-v")
    protected String value;

    @Parameter(names = "-in")
    protected String file;
}
