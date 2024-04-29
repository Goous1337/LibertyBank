package service;

import api.core.RequestParam;
import io.restassured.response.Response;
import pojo.insuranceService.CreateVehicleApplicationInsuranceRequest;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.HEADER;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static constant.CustomerService_2_0_Constants.BEARER_TOKEN;
import static constant.InsuranceServiceConstants.ACCEPT_VALUE;
import static constant.InsuranceServiceConstants.CONTENT_TYPE_VALUE;

import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;
import static property.BaseProperties.ACCESS_TOKEN_INSURANCE_SERVICE;

public class InsuranceService {

    public Response checkMakeNewVehicleApplicationRequest(String clientId, CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest) {
        List<RequestParam> params = List.of(getRP(HEADER, "accept", ACCEPT_VALUE),
                getRP(HEADER, "clientId", clientId),
                getRP(HEADER, CONTENT_TYPE, CONTENT_TYPE_VALUE));
        return sendSimpleRequest(POST, APPLICATION_INSURANCE, params, createVehicleApplicationInsuranceRequest);
    }

    public Response checkGetInfoAboutInsuranceProducts(String typeOfInsurance) {
List<RequestParam> params = List.of(getRP(HEADER, "accept", ACCEPT_VALUE),
        getRP(HEADER,CONTENT_TYPE,CONTENT_TYPE_VALUE));
return sendSimpleRequest(GET,LIST_OF_INSURANCE+typeOfInsurance,params);
    }

    public Response checkGetListOfInsurancePolices(String clientId) {
        List<RequestParam> params = List.of(getRP(HEADER, "accept", ACCEPT_VALUE),
                getRP(HEADER, "clientId", clientId),
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_INSURANCE_SERVICE),
                getRP(HEADER, CONTENT_TYPE, CONTENT_TYPE_VALUE));
        return sendSimpleRequest(GET, LIST_OF_INSURANCE_POLICES, params);
    }

    public Response checkGetListOfInsurancePolicesWithoutClientId() {
        List<RequestParam> params = List.of(getRP(HEADER, "accept", ACCEPT_VALUE),
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_INSURANCE_SERVICE),
                getRP(HEADER, CONTENT_TYPE, CONTENT_TYPE_VALUE));
        return sendSimpleRequest(GET, LIST_OF_INSURANCE_POLICES, params);
    }
}
