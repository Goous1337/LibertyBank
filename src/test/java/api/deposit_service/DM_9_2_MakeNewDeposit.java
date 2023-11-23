package api.deposit_service;

import api.BaseTest;
import dataBase.requests.CustomerServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.DEPOSIT_SERVICE;

@DisplayName("DM-9.2 Оформление нового депозита")
public class DM_9_2_MakeNewDeposit extends BaseTest {
    {
        RestAssured.baseURI = DEPOSIT_SERVICE;
    }
    @DisplayName("Оформление нового депозита")
    @Description("Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита"+
            " авторизованным пользователем.")
    @Tags({@Tag("API"),@Tag("smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-805")
    @ParameterizedTest(name = "Id депозита: {0}, Сумма: {1}, Срок депозита: {2}, Пролонгация депозита: {3}")
    @CsvSource({"1","19000.0000","24","false"})
    public void checkMakeNewDeposit(Integer depositProductId,Double initialAmount,String periodMonths, Boolean autoRenewal){

    }

}
