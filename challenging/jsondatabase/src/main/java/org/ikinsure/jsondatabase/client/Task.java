package org.ikinsure.jsondatabase.client;

import com.beust.jcommander.Parameter;

import java.util.List;

public class Task implements Sendable {

    @Parameter(names = "-t", required = true)
    private String command;

    @Parameter(names = "-i")
    private int index = Integer.MIN_VALUE;

    @Parameter(names = "-m", variableArity = true)
    private List<String> messages;

    @Override
    public String request() {
        return command + (index == Integer.MIN_VALUE ? "" : " " + index) +
                (messages == null ? "" : " " + String.join(" ", messages));
    }
}
