package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.customerService2_0.NotificationStatus;

import java.util.List;
import java.util.Map;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
import static api.core.RequestParamType.*;
import static api.utils.GsonHelper.createBody;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static constant.CustomerServiceConstants.PARAMETER_CUSTOMER_ID;
import static constant.CustomerServiceConstants.PARAMETER_NOTIFICATION_STATUS;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.PATCH;
import static org.apache.commons.lang3.StringUtils.SPACE;

public class CustomerService2_0 {

    public Response checkGettingUserInformation(String customerId) {
        return sendSimpleRequest(GET, RETRIEVING_USER_INFO, getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
    }

    public Response checkGettingUserInformationWithInvalidMethod(String invalidMethod, String customerId) {
        return sendSimpleRequest(Method.valueOf(invalidMethod), RETRIEVING_USER_INFO, getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
    }

    public Response checkPushNotification(String customerId, Boolean notificationStatus) {
        String body = createBody(Map.of(PARAMETER_NOTIFICATION_STATUS,notificationStatus));
        List<RequestParam> params = List.of(getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON), getRP(BODY, SPACE, body));
        return sendSimpleRequest(PATCH, PUSH_NOTIFICATION_2_0, params);
    }

    public Response checkPushNotificationWithNotBoolean(String customerId, String notificationStatus) {
        String body = createBody(Map.of(PARAMETER_NOTIFICATION_STATUS,notificationStatus));
        List<RequestParam> params = List.of(getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON), getRP(BODY, SPACE, body));
        return sendSimpleRequest(PATCH, PUSH_NOTIFICATION_2_0, params);
    }

    public Response checkPushNotificationWithHttpMethod(String method, String customerId, NotificationStatus notificationStatus) {
        List<RequestParam> params = List.of(getRP(QUERY_PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(Method.valueOf(method), PUSH_NOTIFICATION_2_0, params, notificationStatus);
    }

    public Response checkPushNotificationWithInvalidURL(String customerId, Boolean notificationStatus) {
        String body = createBody(Map.of(PARAMETER_NOTIFICATION_STATUS, notificationStatus));
        List<RequestParam> params = List.of(getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId)
                , getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON), getRP(BODY, SPACE, body));
        return sendSimpleRequest(PATCH, INVALID_PUSH_NOTIFICATION_2_0, params);
    }

    public Response checkPushNotificationWithNull(NotificationStatus notificationStatus, String customerId) {
        List<RequestParam> params = List.of(getRP(PARAMETER, PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(PATCH, PUSH_NOTIFICATION_2_0, params, notificationStatus);
    }
}
