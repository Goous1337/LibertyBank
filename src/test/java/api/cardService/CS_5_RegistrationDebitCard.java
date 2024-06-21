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
import pojo.cardService.RegisterBadRequest;

import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_CREATED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;
import static service.CardService.createRegistryDebCard;
import static specs.RequestBody.*;
import static specs.Specs.response400;

@Tags({@Tag("API"), @Tag("MVP")})
@DisplayName("CS-5 Регистрация в системе дебетовой карты")
public class CS_5_RegistrationDebitCard extends BaseTest {
    public static String JSON_SCHEMA_WITH_CARDS_1 = "schemas/cardService/successCreateCard.json";

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @TmsLink("LIB2-1042")
    @DisplayName("Регистрация в системе дебетовой карты")
    @Description("Тест направлен на проверку возможности регистрации в системе дебетовой карты")
    public void reissueCardTest() {
        Response response = cardService.createRegistryDebCard(REGISTER_DEBIT_BODY);

        assertAll(
                () -> assertEquals(SC_CREATED, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_WITH_CARDS_1)));
    }

    @Test
    @TmsLink("LIB2-1504")
    @DisplayName("Карта с невалидными атрибутами")
    @Description("Тест направлен на проверку возможности регистрации дебетовой карты с невалидными атрибутами")
    public void reissueCardInvalidBodyTest() {
        RegisterBadRequest response = createRegistryDebCard(REGISTER_DEBIT_BODY_INVALID_CUSTOMER_ID)
                .then()
                .spec(response400)
                .extract().as(RegisterBadRequest.class);
        assertEquals(SC_BAD_REQUEST, response.getStatus());
    }

    @Test
    @TmsLink("LIB2-1517")
    @DisplayName("Регистрация карты с полем не допускающим значение NULL")
    @Description("Тест направлен на проверку возможности регистрации дебетовой карты с полем не допускающим значение NULL ")
    public void reissueCardNotFoundTest() {
        RegisterBadRequest response = createRegistryDebCard(REGISTER_DEBIT_BODY_NULL_CUSTOMER_ID)
                .then()
                .spec(response400)
                .extract().as(RegisterBadRequest.class);
        assertEquals(SC_BAD_REQUEST, response.getStatus());
    }
}

