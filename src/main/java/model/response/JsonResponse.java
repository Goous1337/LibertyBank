package model.response;

import org.json.JSONObject;

import io.restassured.http.Headers;
import lombok.Getter;

/**
 * Возвращаемый тип HTTP-запроса
 */
@Getter
public class JsonResponse {

    private final JSONObject jsonObject;
    private final Headers headers;
    private final int statusCode;

    /**
     * @param jsonObject это коллекция типа ключ-значения элементов Json
     * @param headers    это заголовки ответа
     * @param statusCode это код ответа
     */
    public JsonResponse(JSONObject jsonObject, Headers headers, int statusCode) {
        this.jsonObject = jsonObject;
        this.headers = headers;
        this.statusCode = statusCode;
    }
}