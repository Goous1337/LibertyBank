package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.PARAMETER;
import static constant.ApiEndpoints.RETRIEVING_USER_INFO;
import static constant.CustomerServiceConstants.PARAMETER_CUSTOMER_ID;
import static io.restassured.http.Method.GET;

public class CustomerService2_0 {

    public Response checkGettingUserInformation(String customerId) {
        return sendSimpleRequest(GET, RETRIEVING_USER_INFO, new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
    }

    public Response checkGettingUserInformationWithInvalidMethod(String invalidMethod, String customerId) {
        return sendSimpleRequest(Method.valueOf(invalidMethod), RETRIEVING_USER_INFO, new RequestParam(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
    }
}
