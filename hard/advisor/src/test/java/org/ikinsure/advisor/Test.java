package org.ikinsure.advisor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Test {

    static String data = "{\n" +
            "  \"display_name\" : \"nodusclientpl\",\n" +
            "  \"external_urls\" : {\n" +
            "    \"spotify\" : \"https://open.spotify.com/user/nodusclientpl\"\n" +
            "  },\n" +
            "  \"followers\" : {\n" +
            "    \"href\" : null,\n" +
            "    \"total\" : 1\n" +
            "  },\n" +
            "  \"href\" : \"https://api.spotify.com/v1/users/nodusclientpl\",\n" +
            "  \"id\" : \"nodusclientpl\",\n" +
            "  \"images\" : [ {\n" +
            "    \"height\" : null,\n" +
            "    \"url\" : \"https://i.scdn.co/image/ab6775700000ee85a33be362c5b5359e2084eae1\",\n" +
            "    \"width\" : null\n" +
            "  } ],\n" +
            "  \"type\" : \"user\",\n" +
            "  \"uri\" : \"spotify:user:nodusclientpl\"\n" +
            "}";

    public static void main(String[] args) {
        System.out.println(data);
        JsonObject json = new Gson().fromJson(data, JsonObject.class);

        System.out.println(json.get("followers").getAsJsonObject().get("total").getAsString());

    }
}
