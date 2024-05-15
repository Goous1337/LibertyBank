package api.cardService;

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

import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;

@Tags({@Tag("API"), @Tag("MVP")})
@DisplayName("CS-1 Просмотр карточных продуктов")
public class CS_1_CheckAllCardProductsTest extends BaseTest {
    public static final String JSON_SCHEMA = "schemas/cardService/checkAllCardProducts.json";

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @TmsLink("LIB2-2769")
    @DisplayName("Просмотр карточных продуктов")
    @Description("Тест направлен на проверку просмотра карточных продуктов")
    public void checkAllCardProducts() {
        Response response = cardService.getCardsInfo();
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA))
        );
    }
}
