package web.epic_3;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

@Tag("Web")
@Epic("3 - Кредиты")
@Feature("US-3.5 Просмотр подробной информации о действующих кредитах пользователя")
@DisplayName("US-3.5 Просмотр подробной информации о действующих кредитах пользователя")
public class US_3_5_CheckBasicInfoMyCredit extends BaseTest {

    @BeforeEach
    public void setUpTest() {
        authorization();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр отображения веб элементов в подробной информации о действующих кредитах пользователя")
    @TmsLink("LIB3-928")
    public void checkBasicInformationAboutMyCredit() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        myCreditSteps.clickShowMoreAboutMyCreditButton();
        myCreditDetailedInformationSteps.assertTextNameTitleCreditIsDisplayed();
        myCreditDetailedInformationSteps.assertTextAccountNumberCreditIsDisplayed();
        myCreditDetailedInformationSteps.assertTextStatusCreditIsDisplayed();
        myCreditDetailedInformationSteps.assertTextNextPaymentCreditIsDisplayed();
        myCreditDetailedInformationSteps.assertTextPaymentDateCreditTextIsDisplayed();
        myCreditDetailedInformationSteps.assertTextLimitCreditIsDisplayed();
        myCreditDetailedInformationSteps.assertTextRepayCreditIsDisplayed();
        myCreditDetailedInformationSteps.assertTextPeriodMonthsCreditIsDisplayed();
        myCreditDetailedInformationSteps.assertTextInterestRateCreditIsDisplayed();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о действующем кредите пользователя (сравнение UI c BackEnd)")
    @TmsLink("LIB3-928")
    public void checkBasicInformationMyCredit() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        myCreditSteps.clickShowMoreAboutMyCreditButton();
        myCreditDetailedInformationSteps.assertMoreInfoAboutCreditProducts();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Просмотр подробной информации о кредите при нажатии на Action bar('Многоточие')")
    @TmsLink("LIB3-930")
    public void checkInformationDotsButton() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        myCreditSteps.clickShowMoreAboutMyCreditButton();
        myCreditDetailedInformationSteps.clickDotsButton();
        myCreditDetailedInformationSteps.assertButtonRequisitesIsDisplayed();
        myCreditDetailedInformationSteps.assertButtonPaymentScheduleIsDisplayed();
    }

    @Test
    @Tags({@Tag("Web"), @Tag("Positive")})
    @DisplayName("Проверка возможности скопировать 'Номер счета'")
    @TmsLink("LIB3-929")
    public void checkCopyAccountNumberText() {
        creditInfoSteps.clickCreditButton();
        creditInfoSteps.clickMyCreditButton();
        myCreditSteps.clickShowMoreAboutMyCreditButton();
        myCreditDetailedInformationSteps.clickCopyAccountNumberCreditButton();
        myCreditDetailedInformationSteps.assertTextOutputCopiedIsDisplayed();
    }
}
