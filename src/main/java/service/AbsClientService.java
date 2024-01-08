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
                (new RequestParam(PARAMETER, SERIES, series), new RequestParam(PARAMETER, NUMBER, number));
        return sendSimpleRequest(GET, ABS_CLIENT_SHORT_INFO, params);
    }

    public Response checkListObtainingClientDataUsingUuid(String uuid) {
        return sendSimpleRequest(GET, ABS_CLIENT_SHORT_INFO, getRP(PARAMETER, UUID_SHORT_INFO, uuid));
    }

    public Response checkListObtainingClientDataUsingPassportAndUuid(String uuid, String series, String number) {
        List<RequestParam> params = List.of
                (new RequestParam(PARAMETER, UUID_SHORT_INFO, uuid), new RequestParam(PARAMETER, SERIES, series),
                        new RequestParam(PARAMETER, NUMBER, number));
        return sendSimpleRequest(GET, ABS_CLIENT_SHORT_INFO, params);
    }
}
