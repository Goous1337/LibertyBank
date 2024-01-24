package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.customerService.UserQuestion;
import pojo.customerService_2_0.CustomerService_2_0_InvalidMobilePhoneValue;
import pojo.customerService_2_0.CustomerService_2_0_Mobile;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.*;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static constant.CustomerServiceConstants.PARAMETER_CUSTOMER_ID;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.PATCH;

public class CustomerService_2_0 {
    public Response checkListSavingVerificationCode(CustomerService_2_0_Mobile customerService_2_0_mobile0Mobile){
        return sendSimpleRequest(PATCH,CUSTOMER_SECURITY,customerService_2_0_mobile0Mobile);
    }
    public Response checkListSavingVerificationCodeWithInvalidMobilePhoneType(CustomerService_2_0_InvalidMobilePhoneValue customerService_2_0_mobile0Mobile){
        return sendSimpleRequest(PATCH,CUSTOMER_SECURITY,customerService_2_0_mobile0Mobile);
    }

    public Response  checkUpdateQuestionAnswer(String question, String answer,String customerId){
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new RequestParam(PARAMETER,PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(PATCH, QUESTION_ANSWER_2_0, params, new UserQuestion(question, answer));
    }

    public Response updateQuestionAnswerInvalidHttpMethod(String question, String answer, String invalidHttpMethod) {
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
        return sendSimpleRequest(Method.valueOf(invalidHttpMethod), QUESTION_ANSWER_2_0, params, new UserQuestion(question, answer));
    }

    public Response checkUpdateQuestionAnswerInvalidUrl(String question, String answer,String customerId){
        List<RequestParam> params = List.of(new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON),
                new RequestParam(PARAMETER,PARAMETER_CUSTOMER_ID, customerId));
        return sendSimpleRequest(PATCH, QUESTION_ANSWER_INVALID_URL_2_0, params, new UserQuestion(question, answer));
    }
}
