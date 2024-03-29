package api.utils;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.InputStream;


public class JsonParser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    public static <T> T parseJson(Class<T> classToParseTo, String json) {
        InputStream stream = classToParseTo.getResourceAsStream(json);
        return MAPPER.readValue(stream, classToParseTo);
    }
}
