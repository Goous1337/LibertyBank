package api.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {

    public static String createBody(Map<String, ?> params) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(params);
    }

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        params.put("", true);

        String body = GsonHelper.createBody(params);
        System.out.println(body);
    }
}
