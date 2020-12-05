package org.ikinsure.hard.search;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface Mappable {

    String[] fields();

    static Map<String, ArrayList<Integer>> invertedIndex(List<? extends Mappable> data) {
        Map<String, ArrayList<Integer>> result = new LinkedHashMap<>();
        for (int i = 0; i < data.size(); i++) {
            for (String field : data.get(i).fields()) {
                if (result.containsKey(field)) {
                    result.get(field).add(i);
                } else {
                    if (!field.isEmpty()) {
                        result.put(field, new ArrayList<>(List.of(i)));
                    }
                }
            }
        }
        return result;
    }
}
