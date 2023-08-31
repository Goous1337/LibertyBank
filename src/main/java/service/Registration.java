package service;

import api.core.ApiClient;
import api.core.RequestParam;
import io.restassured.response.Response;

import java.util.List;
import static constant.ApiEndpoints.REGISTRATION;
import static io.restassured.http.Method.GET;

public class Registration extends ApiClient {

    public static Response checkRegistrationByPhone(List<RequestParam> params) {
        return sendSimpleRequest(GET, REGISTRATION, params);
    }
}