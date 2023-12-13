package service;

import api.core.RequestParam;
import io.restassured.response.Response;
import pojo.depositService.DepositData;
import pojo.depositService.DepositDataCalculator;
import pojo.depositService.DepositDataIncorrectValues;

import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.HEADER;
import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static constant.ApiEndpoints.*;
import static constant.DepositConstants.INVALID_ACCESS_TOKEN;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;
import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;

public class DepositService {
    public Response checkListCurrentDepositProducts() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentDepositProductsInvalidEndpoint() {
        return sendSimpleRequest(GET, INVALID_DEPOSIT_PRODUCTS,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentDepositProductEmptyToken() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS,
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
    }

    public Response checkListMakeNewDeposit
            (Integer depositProductId, Float initialAmount, String periodMonths, Boolean autoRenewal) {
        List<RequestParam> param = List.of
                (new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest
                (POST, DEPOSIT_SETTINGS, param, new DepositData(depositProductId, initialAmount, periodMonths, autoRenewal));
    }

    public Response checkMakeNewDepositInvalidRequest
            (Integer depositProductId, Float initialAmount, Boolean autoRenewal) {
        List<RequestParam> param = List.of
                (new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest
                (POST, DEPOSIT_SETTINGS, param, new DepositData(depositProductId, initialAmount, autoRenewal));
    }

    public Response checkListMakeNewDepositInvalidToken
            (Integer depositProductId, Float initialAmount, String periodMonths, Boolean autoRenewal) {
        List<RequestParam> param = List.of
                (new RequestParam(HEADER, AUTHORIZATION, INVALID_ACCESS_TOKEN));
        return sendSimpleRequest
                (POST, DEPOSIT_SETTINGS, param, new DepositData
                        (depositProductId, initialAmount, periodMonths, autoRenewal));
    }

    public Response checkListValidationDepositAmountIncorrectValues
            (Integer depositProductId, String initialAmount, String periodMonths, Boolean autoRenewal) {
        List<RequestParam> param = List.of
                (new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest
                (POST, DEPOSIT_SETTINGS, param, new DepositDataIncorrectValues
                        (depositProductId, initialAmount, periodMonths, autoRenewal));
    }

    public Response checkListCurrentDepositProductsUsers() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS_USER,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentDepositProductUserEmptyToken() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS_USER,
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
    }

    public Response checkListPositiveBoundaryValueScenarios
            (Integer depositProductId, Integer initialSum, Integer termTime, Boolean isCapitalisation) {
        List<RequestParam> param = List.of
                (new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest
                (POST, DEPOSIT_CALCULATOR, param, new DepositDataCalculator(depositProductId, initialSum, termTime, isCapitalisation));
    }

    public Response checkListCalculatingOfPotentialIncomeOnDepositWithoutId
            (Integer initialSum, Integer termTime, Boolean isCapitalisation) {
        List<RequestParam> param = List.of
                (new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest
                (POST, DEPOSIT_CALCULATOR, param, new DepositDataCalculator(initialSum, termTime, isCapitalisation));
    }

    public Response checkListCalculatingOfPotentialIncomeOnDepositWithoutCapitalisation
            (Integer depositProductId, Integer initialSum, Integer termTime) {
        List<RequestParam> param = List.of
                (new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest
                (POST, DEPOSIT_CALCULATOR, param, new DepositDataCalculator(depositProductId, initialSum, termTime));
    }

    public Response checkListCalculatingOfPotentialIncomeOnDepositWithoutInitialSum
            (Integer depositProductId, Integer termTime, Boolean isCapitalisation) {
        List<RequestParam> param = List.of
                (new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest
                (POST, DEPOSIT_CALCULATOR, param, new DepositDataCalculator(depositProductId, termTime, isCapitalisation));
    }

    public Response checkListCalculatingOfPotentialIncomeOnDepositWithoutTermTime
            (Integer depositProductId, Integer initialSum, Boolean isCapitalisation) {
        List<RequestParam> param = List.of
                (new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
        return sendSimpleRequest
                (POST, DEPOSIT_CALCULATOR, param, new DepositDataCalculator(depositProductId, initialSum, isCapitalisation));
    }

    public Response checkDetailedInformationAboutDeposit(Integer productId) {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS_OFFER + productId,
                new RequestParam(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }
}
