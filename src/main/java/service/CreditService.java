package service;

import api.core.RequestParam;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import pojo.creditService.CreateApplyingLoanRequest;
import pojo.creditService.InvalidDataResponse;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.HEADER;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static constant.ApiEndpoints.CREDIT_BODY;
import static constant.DepositConstants.INVALID_ACCESS_TOKEN;
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
}
