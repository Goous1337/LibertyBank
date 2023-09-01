package service;

import java.util.List;

import api.core.RequestParam;
import io.restassured.response.Response;

import static api.core.ApiClient.sendSimpleRequest;
import static constant.ApiEndpoints.REGISTRATION;
import static io.restassured.http.Method.GET;

public class RegistrationService {

    public Response checkRegistrationByPhone(List<RequestParam> params) {
        return sendSimpleRequest(GET, REGISTRATION, params);
    }
}