package org.ikinsure.jsondatabase.client;

import com.beust.jcommander.Parameter;

public class Task {
    @Parameter(names = "-t", required = true)
    private String type;

    @Parameter(names = "-k")
    private String key;

    @Parameter(names = "-v")
    private String value;
}
