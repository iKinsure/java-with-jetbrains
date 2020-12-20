package org.ikinsure.advisor.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * class represents Spotify category
 */
public class Category implements Printable {

    private final String category;

    public Category(String category) {
        this.category = category;
    }

    @Override
    public void print() {
        System.out.println(category);
    }

    public static class Parser implements Parseable {
        @Override
        public List<? extends Printable> parse(String data) {
            JsonArray array = new Gson().fromJson(data, JsonObject.class).getAsJsonObject("categories").getAsJsonArray("items");
            List<Category> categories = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                categories.add(new Category(array.get(i).getAsJsonObject().get("name").getAsString()));
            }
            return categories;
        }
    }
}
