package api.depositService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
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
import static property.BaseProperties.DEPOSIT_SERVICE;

@DisplayName("Получение подробной информации о депозитном предложении")
public class DM_9_3_CheckDetailedInformationDepositTest extends BaseTest {
    {
        RestAssured.baseURI = DEPOSIT_SERVICE;
    }

    @DisplayName("Получение подробной информации о депозитном предложении")
    @Description("Данный тест-кейс направлен на проверку корректного получения подробной информации о выбранном депозитном предложении.")
    @Tags({@Tag("API"),@Tag("Positive"), @Tag("backend")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-1099")
    @ParameterizedTest(name = "productId: {0}")
    @CsvSource({
            "1",
            "2",
            "3"
    })

    public void checkDetailedInformationAboutDepositOffer(Integer productId) {
        Response response = depositService.checkDetailedInformationAboutDeposit(productId);
        String jsonSchemaPath ="schemas/depositService/successfulGetDepositInfo.json";
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение подробной информации о депозитном предложении при невалидных значениях")
    @Description("Данный тест-кейс направлен на проверку корректного получения подробной информации о выбранном депозитном предложении.")
    @Tags({@Tag("API"),@Tag("Positive"), @Tag("backend")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-1099")
    @Test

    public void DetailedInformationAboutDepositOffer(Integer productId) {
        Response response = depositService.checkDetailedInformationAboutDeposit(productId);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

}
