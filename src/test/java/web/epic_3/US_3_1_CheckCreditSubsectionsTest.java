package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("3 - Кредиты")
@Feature("US-3.1 Просмотр действующих кредитных продуктов пользователя в личном кабинете")
@DisplayName("US-3.1 Просмотр действующих кредитных продуктов пользователя в личном кабинете")
public class US_3_1_CheckCreditSubsectionsTest extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @Test
    @DisplayName("Отображение соответствующих подразделов в разделе Кредиты")
    @TmsLink("LIB3-65")
    public void checkButtonForCreditPage() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.assertUrlMyCretits();
        creditInfoSteps.assertButtonMyCreditIsDisplayed();
        creditInfoSteps.assertButtonCreditProductsBankIsDisplayed();
        creditInfoSteps.assertButtonSubmittedCreditRequestIsDisplayed();
    }

    @Test
    @DisplayName("Отображение действующих кредитов пользователя в подразделе Мои кредиты")
    @TmsLink("LIB3-67")
    public void checkBasicInfoMyCredit() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        myCreditSteps.assertTextMyCreditSumIsDisplayed();
        myCreditSteps.assertTextNameCreditIsDisplayed();
        myCreditSteps.assertTextTermCreditIsDisplayed();
        myCreditSteps.assertButtonShowMoreAboutCreditIsDisplayed();
    }

    @Test
    @DisplayName("Осуществление перехода на страницу с действующими кредитами банка из раздела 'Кредиты'")
    @TmsLink("LIB3-806")
    public void checkUrlCreditProduct() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickCreditProductButton();
        creditInfoSteps.assertUrlCreditProduct();
    }

    @Test
    @DisplayName("Осуществление перехода на страницу с поданными кредитными заявками из раздела 'Кредиты'")
    @TmsLink("LIB3-807")
    public void checkUrlSubmittedCreditRequest() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickSubmittedCreditRequestButton();
        creditInfoSteps.assertUrlSubmittedCreditRequest();
    }

    @Test
    @DisplayName("Осуществление перехода на страницу просмотра подробной информации о кредитном продукте банка по кнопке 'Показать больше'")
    @TmsLink("LIB3-808")
    public void checkShowMoreButtonInMyCreditPage() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        myCreditSteps.clickShowMoreAboutMyCreditButton();
        creditInfoSteps.assertUrlFirstMyCredit();
    }

}
