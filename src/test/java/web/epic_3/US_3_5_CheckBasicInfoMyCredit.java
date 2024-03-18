package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.5 Просмотр подробной информации о действующих кредитах пользователя")
public class US_3_5_CheckBasicInfoMyCredit extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о действующих кредитах пользователя")
    @TmsLink("LIB3-928")
    public void checkBasicInfoAboutMyCredit() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        creditInfoSteps.clickShowMoreAboutMyCreditButton();
        creditInfoSteps.assertTextNameTitleCreditIsDisplayed();
        creditInfoSteps.assertTextAccountNumberCreditIsDisplayed();
        creditInfoSteps.assertTextStatusCreditIsDisplayed();
        creditInfoSteps.assertTextNextPaymentCreditIsDisplayed();
        creditInfoSteps.assertTextPaymentDateCreditTextIsDisplayed();
        creditInfoSteps.assertTextLimitCreditIsDisplayed();
        creditInfoSteps.assertTextRepayCreditIsDisplayed();
        creditInfoSteps.assertTextPeriodMonthsCreditIsDisplayed();
        creditInfoSteps.assertTextInterestRateCreditIsDisplayed();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредите при нажатии на Action bar('Многоточие')")
    @TmsLink("LIB3-930")
    public void checkInfoDotsButton() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        creditInfoSteps.clickShowMoreAboutMyCreditButton();
        creditInfoSteps.clickDotsButton();
        creditInfoSteps.assertButtonRequisitesIsDisplayed();
        creditInfoSteps.assertButtonPaymentScheduleIsDisplayed();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка возможности скопировать 'Номер счета'")
    @TmsLink("LIB3-929")
    public void checkCopyAccountNumberText() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        creditInfoSteps.clickShowMoreAboutMyCreditButton();
        creditInfoSteps.clickCopyAccountNumberCreditButton();
        creditInfoSteps.assertTextOutputCopiedIsDisplayed();
    }
}
