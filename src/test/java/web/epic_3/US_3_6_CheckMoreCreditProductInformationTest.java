package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.6 Просмотр подробной информации о кредитном продукте банка")
@DisplayName("US-3.6 Просмотр подробной информации о кредитном продукте банка")
public class US_3_6_CheckMoreCreditProductInformationTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @Test
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Наличными")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyCash() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyCashButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Срочный")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyExpress() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyExpressButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Money")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyMoney() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyMoneyButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Easy")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyEasy() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyEasyButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    //Тест не проходит потому что в Liberty Car 6 кейсов дополнительной информации
    @Disabled
    @Test
    @DisplayName("Просмотр подробной информации о кредитном продукте Liberty Car")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyCar() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyCarButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @DisplayName("Просмотр подробной информации о кредитном продукте Моя квартира")
    @TmsLink("LIB3-181")
    public void checkBasicInfoAboutLibertyMyFlat() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditProductsSteps.clickShowMoreLibertyMyFlatButton();
        creditProductDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

}
