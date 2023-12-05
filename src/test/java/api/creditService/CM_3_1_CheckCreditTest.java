package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CREDIT_SERVICE;


public class CM_3_1_CheckCreditTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }
    @DisplayName("Получение информации по действующему кредиту пользователя")
    @Description("Данный тест-кейс направлен на проверку CM 3.1 по US 3.1 на получение краткой информации по действующим кредитам авторизованного пользователя")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-84")
    @Test

    public void successfulGetUserCreditInfo() {
        Response response = creditService.checkCreditInfo();
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }
}
