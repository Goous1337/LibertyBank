package service;

import api.core.RequestParam;
import io.restassured.response.Response;
import pojo.insuranceService.*;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.HEADER;
import static com.google.common.net.HttpHeaders.*;
import static constant.ApiEndpoints.APPLICATION_INSURANCE;
import static constant.InsuranceServiceConstants.*;
import static io.restassured.http.Method.POST;

public class InsuranceService {

    public Response checkMakeNewApplicationRequest(CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest) {
        String clientId = "813f5509-7696-44be-a321-3a094c48a6e7";
        List<RequestParam> params = List.of(getRP(HEADER, "accept", ACCEPT_VALUE),
                getRP(HEADER, "clientId", clientId),
                getRP(HEADER, CONTENT_TYPE, CONTENT_TYPE_VALUE));
        return sendSimpleRequest(POST, APPLICATION_INSURANCE, params, createVehicleApplicationInsuranceRequest);
    }
}
