package web.epic_3;

import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

public class US_3_6_ChechCreditProductInformationTest extends BaseTest {

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
        creditInfoSteps.clickShowMoreLibertyCashButton();
        creditInfoSteps.assertTextNameCreditProductPageText();
        creditInfoSteps.assertInterestRateCreditProductPageText();
    }
    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Срочный")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyExpress() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditInfoSteps.clickShowMoreLibertyExpressButton();
        creditInfoSteps.assertTextNameCreditProductPageText();
        creditInfoSteps.assertInterestRateCreditProductPageText();
    }
    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Money")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyMoney() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditInfoSteps.clickShowMoreLibertyMoneyButton();
        creditInfoSteps.assertTextNameCreditProductPageText();
        creditInfoSteps.assertInterestRateCreditProductPageText();
    }
}
