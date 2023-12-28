package service;

import java.util.List;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import pojo.creditService.CreateApplyingLoanRequest;

import static api.core.ApiClient.sendRequestWithoutParams;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.HEADER;
import static api.core.RequestParamType.PARAMETER;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static constant.ApiEndpoints.*;
import static constant.CustomerServiceConstants.PARAMETER_PRODUCT_ID;
import static constant.ApiEndpoints.*;
import static constant.CreditServiceConstants.EMPTY_TOKEN;
import static constant.DepositConstants.INVALID_ACCESS_TOKEN;
import static io.restassured.http.Method.*;
import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;
import static property.BaseProperties.INVALID_TOKEN_CREDIT_SERVICE;

public class CreditService {
    public Response checkListCurrentCreditProducts() {
        return sendSimpleRequest(GET, CREDIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentCreditProductsWithInvalidToken(String invalidToken) {
        return sendSimpleRequest(GET, CREDIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, invalidToken));
    }

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

    public Response checkListCurrentCreditProductsIncorrectRequestConfiguration() {
        return sendSimpleRequest(GET, INVALID_CREDIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListWithdrawalOfLoanApplication(Integer idValue) {
        return sendSimpleRequest(DELETE, CREDIT_WITHDRAWAL + idValue,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListWithdrawalOfLoanApplicationNotExistParamOfId() {
        return sendSimpleRequest(DELETE, NOT_EXIST_CREDIT_WITHDRAWAL,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListWithdrawalOfLoanApplicationEmptyToken() {
        return sendSimpleRequest(DELETE, NOT_EXIST_CREDIT_WITHDRAWAL,
                new RequestParam(HEADER, AUTHORIZATION, EMPTY_TOKEN));
    }
    public static Response checkGetRequestDisplayingElectronicBackground(String productId) {
        List<RequestParam> param = List.of(new RequestParam(PARAMETER, PARAMETER_PRODUCT_ID, productId),
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest(Method.GET, CREDIT_BACKGROUND, param);
    }

    public static Response checkGetRequestDisplayingElectronicBackgroundInvalidToken(String productId) {
        List<RequestParam> param = List.of(new RequestParam(PARAMETER, PARAMETER_PRODUCT_ID, productId),
                new RequestParam(HEADER, AUTHORIZATION, INVALID_TOKEN_CREDIT_SERVICE));
        return sendSimpleRequest(Method.GET, CREDIT_BACKGROUND,param);
    }

    public  Response checkCreditInfo() {
        return sendSimpleRequest(GET, CREDIT_INFO,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }
    public  Response checkCreditInfoNoToken() {
        return sendRequestWithoutParams(GET, CREDIT_INFO);
    }
}