package service;

import api.core.RequestParam;
import io.restassured.response.Response;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.PARAMETER;
import static constant.AbsClientServiceConstant.*;
import static constant.ApiEndpoints.ABS_CLIENT_SHORT_INFO;
import static io.restassured.http.Method.GET;

public class AbsClientService {
    public Response checkListObtainingClientDataUsingPassport(String series, String number) {
        List<RequestParam> params = List.of
                (getRP(PARAMETER, SERIES, series), getRP(PARAMETER, NUMBER, number));
        return sendSimpleRequest(GET, ABS_CLIENT_SHORT_INFO, params);
    }

    public Response checkListObtainingClientDataUsingUuid(String uuid) {
        return sendSimpleRequest(GET, ABS_CLIENT_SHORT_INFO, getRP(PARAMETER, UUID_SHORT_INFO, uuid));
    }

    public Response checkListObtainingClientDataUsingPassportAndUuid(String uuid, String series, String number) {
        List<RequestParam> params = List.of
                (getRP(PARAMETER, UUID_SHORT_INFO, uuid), getRP(PARAMETER, SERIES, series),
                        getRP(PARAMETER, NUMBER, number));
        return sendSimpleRequest(GET, ABS_CLIENT_SHORT_INFO, params);
    }
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.absInfoService.AbsInfoServiceDataBankBranch;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.HEADER;
import static api.core.RequestParamType.PARAMETER;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.AbsClientServiceConstants.*;
import static constant.ApiEndpoints.*;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;
import static property.BaseProperties.ABS_CLIENT_SERVICE;
import static property.BaseProperties.INVALID_TOKEN_CREDIT_SERVICE;

public class AbsClientService {
    public Response checkGetPersonalInfoClients(String customer_uuid){
        return sendSimpleRequest(GET,ABS_CLIENT_SERVICE_PERSONAL_DATE, getRP(PARAMETER, PARAMETER_CUSTOMER_UUID, customer_uuid));
    }
    public Response checkGetPersonalInfoClientsWithIncorrectUuid(){
        return sendSimpleRequest(Method.GET, ABS_CLIENT_SERVICE_PERSONAL_DATE,
                getRP(PARAMETER, PARAMETER_CUSTOMER_UUID , PARAMETER_INCORRECT_CUSTOMER_UUID));
    }
    public Response checkGetPersonalInfoClientsWithInvalidUuid(){
        return sendSimpleRequest(Method.GET, ABS_CLIENT_SERVICE_PERSONAL_DATE,
                getRP(PARAMETER, PARAMETER_CUSTOMER_UUID , PARAMETER_INVALID_CUSTOMER_UUID));
    }

}
