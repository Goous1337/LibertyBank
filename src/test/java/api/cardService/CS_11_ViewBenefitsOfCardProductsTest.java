package api.cardService;

import api.BaseTest;
import constant.CardProducts;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.cardService.cs_11.CardProductBenefits;

import static api.utils.JsonParser.parseJson;
import static constant.CardProducts.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CARD_SERVICE;

@DisplayName("CS-11 Просмотр преимуществ карточных продуктов")
public class CS_11_ViewBenefitsOfCardProductsTest extends BaseTest {

    private static final String JSON_BENEFITS_OF_GOLD = "/schemas/cardService/cs_11/successfulGetBenefitsOfLibertyCardGold.json";
    private static final String JSON_BENEFITS_OF_CHILD = "/schemas/cardService/cs_11/successfulGetBenefitsOfLibertyCardChild.json";
    private static final String JSON_BENEFITS_OF_PLATINUM = "/schemas/cardService/cs_11/successfulGetBenefitsOfLibertyCardPlatinum.json";
    private static final String JSON_BENEFITS_OF_VIRTUAL = "/schemas/cardService/cs_11/successfulGetBenefitsOfLibertyCardVirtual.json";
    private static final String JSON_BENEFITS_OF_CLASSIC = "/schemas/cardService/cs_11/successfulGetBenefitsOfLibertyCardClassic.json";
    private static final String JSON_BENEFITS_OF_SECURE = "/schemas/cardService/cs_11/successfulGetBenefitsOfLibertyCardSecure.json";
    private static final String JSON_BENEFITS_OF_TRAVEL = "/schemas/cardService/cs_11/successfulGetBenefitsOfLibertyCardTravel.json";

    {
        RestAssured.baseURI = CARD_SERVICE;
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3225")
    @DisplayName("Просмотр преимуществ карточных продуктов, Liberty Card Gold")
    @Description("Тест направлен на проверку возможности просмотра преимуществ карты Liberty Card Gold")
    public void viewBenefitsOfLibertyCardGold() {
        checkBenefits(LIBERTY_CARD_GOLD, JSON_BENEFITS_OF_GOLD);
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3226")
    @DisplayName("Просмотр преимуществ карточных продуктов, Liberty Card Child")
    @Description("Тест направлен на проверку возможности просмотра преимуществ карты Liberty Card Child")
    public void viewBenefitsOfLibertyCardChild() {
        checkBenefits(LIBERTY_CARD_CHILD, JSON_BENEFITS_OF_CHILD);
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3227")
    @DisplayName("Просмотр преимуществ карточных продуктов, Liberty Card Platinum")
    @Description("Тест направлен на проверку возможности просмотра преимуществ карты Liberty Card Platinum")
    public void viewBenefitsOfLibertyCardPlatinum() {
        checkBenefits(LIBERTY_CARD_PLATINUM, JSON_BENEFITS_OF_PLATINUM);
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3228")
    @DisplayName("Просмотр преимуществ карточных продуктов, Liberty Card Virtual")
    @Description("Тест направлен на проверку возможности просмотра преимуществ карты Liberty Card Virtual")
    public void viewBenefitsOfLibertyCardVirtual() {
        checkBenefits(LIBERTY_CARD_VIRTUAL, JSON_BENEFITS_OF_VIRTUAL);
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3229")
    @DisplayName("Просмотр преимуществ карточных продуктов, Liberty Card Classic")
    @Description("Тест направлен на проверку возможности просмотра преимуществ карты Liberty Card Classic")
    public void viewBenefitsOfLibertyCardSecure() {
        checkBenefits(LIBERTY_CARD_CLASSIC, JSON_BENEFITS_OF_CLASSIC);
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3230")
    @DisplayName("Просмотр преимуществ карточных продуктов, Liberty Card Secure")
    @Description("Тест направлен на проверку возможности просмотра преимуществ карты Liberty Card Secure")
    public void viewBenefitsOfLibertyCardClassic() {
        checkBenefits(LIBERTY_CARD_SECURE, JSON_BENEFITS_OF_SECURE);
    }

    @Test
    @Tag("API")
    @TmsLink("LIB2-3231")
    @DisplayName("Просмотр преимуществ карточных продуктов, Liberty Card Travel")
    @Description("Тест направлен на проверку возможности просмотра преимуществ карты Liberty Card Travel")
    public void viewBenefitsOfLibertyCardTravel() {
        checkBenefits(LIBERTY_CARD_TRAVEL, JSON_BENEFITS_OF_TRAVEL);
    }

    public void checkBenefits(CardProducts cardProducts, String json) {
        CardProductBenefits actualBenefits = cardService.getBenefitsOfCardProducts(cardProducts).as(CardProductBenefits.class);
        CardProductBenefits expectedBenefits = parseJson(CardProductBenefits.class, json);

        assertEquals(expectedBenefits, actualBenefits, "Данные не соответствуют ожидаемым");
    }
}
