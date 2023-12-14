package api.creditService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CREDIT_SERVICE;

public class CM_0_TestingTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Получение информации по действующему кредиту пользователя")
    @Description("Данный тест-кейс направлен на проверку CM 3.1 по US 3.1 на получение краткой информации по действующим кредитам авторизованного пользователя")
    @Tag("my")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-84")
    @Test
    public void successfulGetUserCreditInfo() {
        Response response = creditService.checkCreditInfo();
        String jsonSchemaPath = "schemas/creditService/CM_3_1/successfulGetUserCreditInfo.json";
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
