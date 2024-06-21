package api.cardService;

import api.BaseTest;
import dataBase.requests.CardServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static constant.AccountServiceConstants.STATUS_ACTIVE;
import static constant.CardServiceConstants.INVALID_PRODUCT_TYPE_ID;
import static constant.CardServiceConstants.INVALID_URL_PRODUCT_TYPE;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;

@Tags({@Tag("API"), @Tag("MVP")})
@DisplayName("CS-6 Просмотр подробной информации по карте")
public class CS_6_ViewInformationCardProductTest extends BaseTest {
    public static final String JSON_SCHEMA_VIEW_INFORMATION = "schemas/cardService/checkInformationCardProduct.json";
    public static final String JSON_SCHEMA_FOR_ERROR = "schemas/cardService/errorResponse.json";

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @TmsLink("LIB2-41")
    @DisplayName("Просмотр подробной информации покарточному продукту")
    @Description("Тест направлен на проверку возможности просмотра подробной информации по карточному продукту")
    public void viewInformationCardProduct() {
        String productTypeId = CardServiceDataBaseRequest.getProductTypeId(STATUS_ACTIVE);
        Response response = cardService.getInformationCardProduct(productTypeId);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_VIEW_INFORMATION))
        );
    }

    @Test
    @TmsLink("LIB2-41")
    @DisplayName("Просмотр подробной информации покарточному продукту с невалидным productTypeId")
    @Description("Тест направлен на проверку возможности просмотра подробной информации по карточному продукту с невалидным productTypeId")
    public void viewInformationInvalidCardProduct() {
        Response response = cardService.getInformationCardProduct(INVALID_PRODUCT_TYPE_ID);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому")
        );
    }

    @Test
    @TmsLink("LIB2-41")
    @DisplayName("Просмотр подробной информации покарточному продукту с отсутствующим в системе productTypeId")
    @Description("Тест направлен на проверку возможности просмотра подробной информации по карточному продукту с отсутствующим в системе productTypeId")
    public void viewInformationInvalidUrlCardProduct() {
        Response response = cardService.getInformationCardProduct(INVALID_URL_PRODUCT_TYPE);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_FOR_ERROR))
        );
    }
}
