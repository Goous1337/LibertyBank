package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.1 Просмотр действующих кредитных продуктов пользователя в личном кабинете")
public class US_3_1_CheckCreditSubsectionsTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Отображение соответствующих подразделов в разделе Кредиты")
    @TmsLink("LIB3-65")
    public void checkButtonForCreditPage() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.assertUrlMyCretits();
        creditInfoSteps.assertButtonMyCreditIsDisplayed();
        creditInfoSteps.assertButtonCreditProductsBankIsDisplayed();
        creditInfoSteps.assertButtonSubmittedCreditАpplicationsIsDisplayed();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Отображение действующих кредитов пользователя в подразделе Мои кредиты")
    @TmsLink("LIB3-67")
    public void checkBasicInfoMyCredit() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        creditInfoSteps.assertTextMyCreditSumIsDisplayed();
        creditInfoSteps.assertTextNameCreditIsDisplayed();
        creditInfoSteps.assertTextTermCreditIsDisplayed();
        creditInfoSteps.assertButtonShowMoreAboutCreditIsDisplayed();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Осуществление перехода на страницу с действующими кредитами банка из раздела 'Кредиты'")
    @TmsLink("LIB3-806")
    public void checkUrlCreditProduct() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditInfoSteps.assertUrlCreditProduct();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Осуществление перехода на страницу с поданными кредитными заявками из раздела 'Кредиты'")
    @TmsLink("LIB3-807")
    public void checkUrlSubmittedCreditАpplications() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickSubmittedCreditАpplicationsButton();
        creditInfoSteps.assertUrlSubmittedCreditАpplications();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Осуществление перехода на страницу просмотра подробной информации о кредитном продукте банка по кнопке 'Показать больше'")
    @TmsLink("LIB3-808")
    public void checkShowMoreButtonInMyCreditPage() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        creditInfoSteps.clickShowMoreAboutMyCreditButton();
        creditInfoSteps.assertUrlFirstMyCredit();
    }

}
