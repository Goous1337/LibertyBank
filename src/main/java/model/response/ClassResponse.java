package model.response;

import io.restassured.http.Headers;
import lombok.Getter;

/**
 * Возвращаемый тип HTTP-запроса
 */
@Getter
public class ClassResponse<T> {
    private final T body;
    private final Headers headers;
    private final int statusCode;

    /**
     * @param body       это дженерик, используемый для подстановки класса, в который будет маппиться ответ
     * @param headers    это заголовки ответа
     * @param statusCode это код ответа
     */
    public ClassResponse(T body, Headers headers, int statusCode) {
        this.body = body;
        this.headers = headers;
        this.statusCode = statusCode;
    }
}