package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.Response;
import pojo.creditService.CreateApplyingLoanRequest;


import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.*;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static constant.ApiEndpoints.*;
import static constant.CustomerServiceConstants.*;
import static property.BaseProperties.*;


public class CreditService {

    public Response checkListCurrentCreditProducts() {
        return sendSimpleRequest(GET, CREDIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentCreditProductsWithInvalidToken(String invalidToken) {
        return sendSimpleRequest(GET, CREDIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, invalidToken));
    }

    public Response checkListCurrentCreditProductsIncorrectRequestConfiguration() {
        return sendSimpleRequest(GET, INVALID_CREDIT_PRODUCTS,
import static constant.ApiEndpoints.*;
import static constant.DepositConstants.INVALID_ACCESS_TOKEN;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;
import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;

public class CreditService {
    public Response checkListApplyingLoan
            (Integer productId, Integer amount, Integer periodMonths, String currencyCode, String creationDate,
             Integer monthlyIncome, Integer monthlyExpenditure, String employerIdentificationNumber) {
        List<RequestParam> params = List.of
                (new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest
                (POST, CREDIT_BODY, params, new CreateApplyingLoanRequest(productId, amount, periodMonths,
                        currencyCode, creationDate, monthlyIncome, monthlyExpenditure, employerIdentificationNumber));
    }

    public Response checkListApplyingLoanInvalidToken
            (Integer productId, Integer amount, Integer periodMonths, String currencyCode, String creationDate,
             Integer monthlyIncome, Integer monthlyExpenditure, String employerIdentificationNumber) {
        List<RequestParam> params = List.of
                (new RequestParam(HEADER, AUTHORIZATION, INVALID_ACCESS_TOKEN));
        return sendSimpleRequest
                (POST, CREDIT_BODY, params, new CreateApplyingLoanRequest(productId, amount, periodMonths,
                        currencyCode, creationDate, monthlyIncome, monthlyExpenditure, employerIdentificationNumber));
    }

    public Response checkListNumberOfLoanApplicationsSubmitted() {
        return sendSimpleRequest(GET, CREDIT_ORDER_STATUS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListNumberOfLoanApplicationsSubmittedInvalidToken() {
        return sendSimpleRequest(GET, CREDIT_ORDER_STATUS,
                new RequestParam(HEADER, AUTHORIZATION, INVALID_ACCESS_TOKEN));
    }

    public Response checkListNumberOfLoanApplicationsSubmittedNoRecordsInTheTable() {
        return sendSimpleRequest(GET, INVALID_CREDIT_ORDER_TABLE,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public static Response checkGetRequestDisplayingElectronicBackground(String productId) {
        List<RequestParam> param = List.of(new RequestParam(PARAMETER, PARAMETER_PRODUCT_ID, productId),
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest(Method.GET, CREDIT_BACKGROUND, param);
    }

    public static Response checkGetRequestDisplayingElectronicBackgroundInvalidToken() {
        return sendSimpleRequest(Method.GET, CREDIT_BACKGROUND,
                new RequestParam(HEADER, AUTHORIZATION, INVALID_TOKEN_CREDIT_SERVICE));
    }
}
