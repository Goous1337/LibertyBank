package service;

import api.core.RequestParam;
import io.restassured.response.Response;
import pojo.depositService.DepositCheckEmail;
import pojo.depositService.DepositData;
import pojo.depositService.DepositDataCalculator;
import pojo.depositService.DepositDataIncorrectValues;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParam.getRP;
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
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentDepositProductsInvalidEndpoint() {
        return sendSimpleRequest(GET, INVALID_DEPOSIT_PRODUCTS,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentDepositProductEmptyToken() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS,
                new RequestParam(HEADER, CONTENT_TYPE, APPLICATION_JSON));
    }

    public Response checkListMakeNewDeposit
            (Integer depositProductId, Float initialAmount, String periodMonths, String currencyCode, Boolean autoRenewal) {
        return sendSimpleRequest(POST, DEPOSIT_SETTINGS,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE),
                new DepositData(depositProductId, initialAmount, periodMonths, currencyCode, autoRenewal));
    }

    public Response checkMakeNewDepositInvalidRequest
            (Integer depositProductId, Float initialAmount, String currencyCode, Boolean autoRenewal) {
        return sendSimpleRequest(POST, DEPOSIT_SETTINGS,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE),
                new DepositData(depositProductId, initialAmount, currencyCode, autoRenewal));
    }

    public Response checkListMakeNewDepositInvalidToken
            (Integer depositProductId, Float initialAmount, String periodMonths, String currencyCode, Boolean autoRenewal) {
        return sendSimpleRequest(POST, DEPOSIT_SETTINGS,
                getRP(HEADER, AUTHORIZATION, INVALID_ACCESS_TOKEN),
                new DepositData(depositProductId, initialAmount, periodMonths, currencyCode, autoRenewal));
    }

    public Response checkListValidationDepositAmountIncorrectValues
            (Integer depositProductId, String initialAmount, String periodMonths, Boolean autoRenewal) {

        return sendSimpleRequest(POST, DEPOSIT_SETTINGS,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE),
                new DepositDataIncorrectValues(depositProductId, initialAmount, periodMonths, autoRenewal));
    }

    public Response checkListCurrentDepositProductsUsers() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS_USER,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public Response checkListCurrentDepositProductUserEmptyToken() {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS_USER,
                getRP(HEADER, CONTENT_TYPE, APPLICATION_JSON));
    }

    public Response checkListBoundaryValueScenarios
            (Integer depositProductId, Float initialSum, Integer termTime, Boolean isCapitalisation, String currencyCode) {
        return sendSimpleRequest(POST, DEPOSIT_CALCULATOR,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE),
                new DepositDataCalculator(depositProductId, initialSum, termTime, isCapitalisation, currencyCode));
    }

    public Response checkListCalculatingOfPotentialIncomeOnDepositWithoutId
            (Float initialSum, Integer termTime, Boolean isCapitalisation) {
        return sendSimpleRequest(POST, DEPOSIT_CALCULATOR,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE),
                new DepositDataCalculator(initialSum, termTime, isCapitalisation));
    }

    public Response checkListCalculatingOfPotentialIncomeOnDepositWithoutCapitalisation
            (Integer depositProductId, Float initialSum, Integer termTime) {
        return sendSimpleRequest(POST, DEPOSIT_CALCULATOR,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE),
                new DepositDataCalculator(depositProductId, initialSum, termTime));
    }

    public Response checkListCalculatingOfPotentialIncomeOnDepositWithoutInitialSum
            (Integer depositProductId, Integer termTime, Boolean isCapitalisation) {
        return sendSimpleRequest(POST, DEPOSIT_CALCULATOR,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE),
                new DepositDataCalculator(depositProductId, termTime, isCapitalisation));
    }

    public Response checkListCalculatingOfPotentialIncomeOnDepositWithoutTermTime
            (Integer depositProductId, Float initialSum, Boolean isCapitalisation) {
        return sendSimpleRequest(POST, DEPOSIT_CALCULATOR,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE),
                new DepositDataCalculator(depositProductId, initialSum, isCapitalisation));
    }

    public Response checkDetailedInformationAboutDeposit(Integer productId) {
        return sendSimpleRequest(GET, DEPOSIT_PRODUCTS_OFFER + productId,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE));
    }

    public float calculationFinalDepositAmountIsTrue(float initSum, float insertRate, int termTime) {
        float formula = (1 + (insertRate / 100) / 12);
        float finalValue = (float) (Math.pow(formula, termTime) * initSum);
        return (float) Math.round(finalValue * 100) / 100;
    }

    public float calculationFinalDepositAmountIsFalse(float initSum, float insertRate, int termTime) {
        float formula = (initSum * insertRate * ((float) (termTime * 30) / 365)) / 100;
        return (float) Math.round(formula * 100) / 100;
    }

    public Response checkSendingByEmail(String email, Integer id) {
        return sendSimpleRequest(POST, DEPOSIT_SEND_EMAIL,
                getRP(HEADER, AUTHORIZATION, ACCESS_TOKEN_CUSTOMER_SERVICE),
                new DepositCheckEmail(email, id));
    }
}
