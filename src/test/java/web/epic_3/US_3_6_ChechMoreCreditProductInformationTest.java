package web.epic_3;

import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

public class US_3_6_ChechMoreCreditProductInformationTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Наличными")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyCash() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyCashButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Срочный")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyExpress() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyExpressButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Money")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyMoney() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyMoneyButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Easy")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyEasy() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyEasyButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Car")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyCar() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyCarButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредитном продукте Моя квартира")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyMyFlat() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyMyFlatButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

}
