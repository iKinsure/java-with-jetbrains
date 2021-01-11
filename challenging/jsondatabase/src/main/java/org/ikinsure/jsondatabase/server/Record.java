package org.ikinsure.jsondatabase.server;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class Record {
    @Expose
    String key;
    @Expose
    JsonElement value;
}
