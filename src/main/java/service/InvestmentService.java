package service;

import io.restassured.response.Response;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.HEADER;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static constant.ApiEndpoints.*;
import static constant.CustomerService_2_0_Constants.BEARER_TOKEN;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;

public class InvestmentService {
    public static Response getQuestionnaireRequest(String jwtToken) {
        return sendSimpleRequest(GET, QUESTIONNAIRE_FORM,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + jwtToken));
    }

    public static Response postAuthRequest(String jwtToken) {
        return sendSimpleRequest(POST, INVESTMENT_AUTH,
                getRP(HEADER, AUTHORIZATION, BEARER_TOKEN + jwtToken));
    }
}