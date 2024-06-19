package web.epic_10;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static web.constans.UrlConfig.ONLINE_PROPERTY_CONTENT_APPLICATION_URL;



@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("10 - Страхование")
@Feature("US-10.1.2 Подача заявки на договор страхования онлайн")
@DisplayName("US-10.1.2 Подача заявки на договор страхования домашнего имущества онлайн")
public class US_10_1_2_CreateNewPropertyContentsApplicationOnlineTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ONLINE_PROPERTY_CONTENT_APPLICATION_URL);
    }

    @DisplayName("Подача заявки на страхование домашнего имущества: ввод валидных данных")
    @Description("Тест направлен на проверку валидации формы завки на страхование домашнего имущества при валидных данных")
    @Tag("WEB")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2033")
    @Test()
    public void successfulOnlinePropertyApplicationTest() {
        insuranceApplicationPropertyContentsSteps.assertPageTitle();
        Assertions.assertFalse(insuranceApplicationPropertyContentsSteps.assertNextButtonDisabled());
        insuranceApplicationPropertyContentsSteps.assertCurrencyRubChosen();
        insuranceApplicationPropertyContentsSteps.assertCurrencyEurNotChosen();
        insuranceApplicationPropertyContentsSteps.assertCurrencyUsdNotChosen();
        insuranceApplicationPropertyContentsSteps.inputMinimalInsuranceDuration();
        insuranceApplicationPropertyContentsSteps.choseInsuranceStartingTomorrow();
        insuranceApplicationPropertyContentsSteps.assertNextButtonEnabled();
        insuranceApplicationPropertyContentsSteps.pressNextButton();
        insuranceApplicationPropertyContentsSteps.assertNextButtonDisabled();
        insuranceApplicationPropertyContentsSteps.inputValidLastName();
        insuranceApplicationPropertyContentsSteps.inputValidFirstName();
        insuranceApplicationPropertyContentsSteps.inputValidPatronymic();
        insuranceApplicationPropertyContentsSteps.inputDateOfBirth();
        insuranceApplicationPropertyContentsSteps.inputPhoneNumber();
        insuranceApplicationPropertyContentsSteps.inputEmail();
        insuranceApplicationPropertyContentsSteps.chooseDocumentTypePassport();
        insuranceApplicationPropertyContentsSteps.inputPassportNumber();
        insuranceApplicationPropertyContentsSteps.inputGotDocumentDate();
        insuranceApplicationPropertyContentsSteps.inputDocumentDepartment();
        insuranceApplicationPropertyContentsSteps.assertNextButtonEnabled();
        insuranceApplicationPropertyContentsSteps.pressNextButton();
        insuranceApplicationPropertyContentsSteps.assertNextButtonDisabled();
        insuranceApplicationPropertyContentsSteps.inputThingName();
        insuranceApplicationPropertyContentsSteps.inputThingCost();
        insuranceApplicationPropertyContentsSteps.selectThingType();
        insuranceApplicationPropertyContentsSteps.assertNextButtonEnabled();
        insuranceApplicationPropertyContentsSteps.pressNextButton();
        insuranceApplicationPropertyContentsSteps.assertConfirmButtonDisabled();
        insuranceApplicationPropertyContentsSteps.selectConstructionType();
        insuranceApplicationPropertyContentsSteps.inputCity();
        insuranceApplicationPropertyContentsSteps.inputHouse();
        insuranceApplicationPropertyContentsSteps.inputFloor();
        insuranceApplicationPropertyContentsSteps.inputStreet();
        insuranceApplicationPropertyContentsSteps.inputApartment();
        insuranceApplicationPropertyContentsSteps.inputEntrance();
        insuranceApplicationPropertyContentsSteps.assertConfirmButtonEnabled();
        insuranceApplicationPropertyContentsSteps.pressConfirmButton();
        insuranceApplicationPropertyContentsSteps.assertSuccessImageIsPresent();
    }
}
