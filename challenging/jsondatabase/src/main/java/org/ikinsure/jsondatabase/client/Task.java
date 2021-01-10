package org.ikinsure.jsondatabase.client;

import com.beust.jcommander.Parameter;
import com.google.gson.annotations.Expose;

public class Task {

    @Expose
    @Parameter(names = "-t")
    protected String type;

    @Expose
    @Parameter(names = "-k")
    protected String key;

    @Expose
    @Parameter(names = "-v")
    protected String value;

    @Parameter(names = "-in")
    protected String file;
}
