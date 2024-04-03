package api.cardService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.cardService.RegisterBadRequest;
import pojo.cardService.RegisterDebitResponse;

import static constant.AccountServiceConstants.STATUS_ACTIVE;
import static constant.CardServiceConstants.CUSTOMER_ID_WITH_ACTIVE_CARDS;
import static constant.CardServiceConstants.VALID_PRODUCT_TYPE_ID;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;
import static service.CardService.getRegistrDebCard;
import static specs.RequestBody.*;
import static specs.Specs.response201;
import static specs.Specs.response400;

@DisplayName("CS-5 Регистрация в системе дебетовой карты")
public class CS_5_RegistrationDebitCard extends BaseTest {

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-1042")
    @DisplayName("Регистрация в системе дебетовой карты")
    @Description("Тест направлен на проверку возможности регистрации в системе дебетовой карты")
    public void reissueCardTest() {
        RegisterDebitResponse response = getRegistrDebCard(REGISTER_DEBIT_BODY)
                .then()
                .spec(response201)
                .extract().as(RegisterDebitResponse.class);

        Assertions.assertAll("Verify response fields",
                () -> assertEquals(VALID_PRODUCT_TYPE_ID, response.getProductType()),
                () -> assertEquals("a60f746b-7faf-4bcd-bf5c-8d53c4cf0a3a", response.getAccount()),
                () -> assertEquals(CUSTOMER_ID_WITH_ACTIVE_CARDS, response.getCustomer()),
                () -> assertEquals(null, response.getBalance()),
                () -> assertEquals(false, response.getFavourite()),
                () -> assertEquals(STATUS_ACTIVE, response.getCardStatus()),
                () -> assertEquals(true, response.getEmbossed()),
                () -> assertEquals(true, response.getPermitVirtualPayment()),
                () -> assertEquals(null, response.getClosedAt())
        );
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-1504")
    @DisplayName("Карта с невалидными атрибутами")
    @Description("Тест направлен на проверку возможности регистрации дебетовой карты с невалидными атрибутами")
    public void reissueCardInvalidBodyTest() {
        RegisterBadRequest response = getRegistrDebCard(REGISTER_DEBIT_BODY_INVALID_CUSTOMER_ID)
                .then()
                .spec(response400)
                .extract().as(RegisterBadRequest.class);
        assertEquals(SC_BAD_REQUEST, response.getStatus());
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-1517")
    @DisplayName("Регистрация карты с полем не допускающим значение NULL")
    @Description("Тест направлен на проверку возможности регистрации дебетовой карты с полем не допускающим значение NULL ")
    public void reissueCardNotFoundTest() {
        RegisterBadRequest response = getRegistrDebCard(REGISTER_DEBIT_BODY_NULL_CUSTOMER_ID)
                .then()
                .spec(response400)
                .extract().as(RegisterBadRequest.class);
        assertEquals(SC_BAD_REQUEST, response.getStatus());
    }
}

