package web.epic_10;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import web.BaseTest;
import web.constans.insurance.InsuranceEnum.Currencies;

import static web.constans.insurance.InsuranceServiceConstants.APARTMENT;
import static web.constans.insurance.InsuranceServiceConstants.APARTMENT_AREA;
import static web.constans.insurance.InsuranceServiceConstants.APARTMENT_COST;
import static web.constans.insurance.InsuranceServiceConstants.APARTMENT_INSURANCE_AMOUNT;
import static web.constans.insurance.InsuranceServiceConstants.APRIL;
import static web.constans.insurance.InsuranceServiceConstants.CITY;
import static web.constans.insurance.InsuranceServiceConstants.DOCUMENT_DEPARTMENT;
import static web.constans.insurance.InsuranceServiceConstants.DOCUMENT_NUMBER;
import static web.constans.insurance.InsuranceServiceConstants.DOCUMENT_TYPE_PASSPORT;
import static web.constans.insurance.InsuranceServiceConstants.EMAIL;
import static web.constans.insurance.InsuranceServiceConstants.ENTRANCE;
import static web.constans.insurance.InsuranceServiceConstants.FORTH_DAY_OF_MONTH;
import static web.constans.insurance.InsuranceServiceConstants.HOUSE;
import static web.constans.insurance.InsuranceServiceConstants.INSURANCE_DURATION_MINIMUM;
import static web.constans.insurance.InsuranceServiceConstants.JULY;
import static web.constans.insurance.InsuranceServiceConstants.PHONE_NUMBER;
import static web.constans.insurance.InsuranceServiceConstants.START_OF_CENTURY;
import static web.constans.insurance.InsuranceServiceConstants.STATE;
import static web.constans.insurance.InsuranceServiceConstants.STREET;
import static web.constans.insurance.InsuranceServiceConstants.THIRTY_FIRST_DAY_OF_MONTH;
import static web.constans.insurance.InsuranceServiceConstants.TWENTY_EIGHTEEN;
import static web.constans.insurance.InsuranceServiceConstants.VALID_NAME;
import static web.constans.insurance.InsuranceServiceConstants.YEAR_OF_CONSTRUCTION;
import static web.constans.UrlConfig.ONLINE_APARTMENT_APPLICATION_URL;


@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("10 - Страхование")
@Feature("US-10.1.2 Подача заявки на договор страхования онлайн")
@DisplayName("US-10.1.2 Подача заявки на договор страхования квартиры онлайн")
public class US_10_1_2_CreateNewApartmentApplicationOnlineTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        open(ONLINE_APARTMENT_APPLICATION_URL);
    }

    @DisplayName("Подача заявки на страхование квартиры: ввод валидных данных")
    @Description("Тест направлен на проверку валидации формы завки на страхование квартиры при валидных данных")
    @TmsLink("LIB5-3747")
    @Test
    public void successfulOnlineApartmentApplicationTest() {
        insuranceApplicationApartmentSteps.assertNextButtonIsEnabled(false);
        insuranceApplicationApartmentSteps.inputInsuranceDuration(INSURANCE_DURATION_MINIMUM);
        insuranceApplicationApartmentSteps.choseInsuranceStartingTomorrow();
        insuranceApplicationApartmentSteps.pressPayPartsSwitch();
        insuranceApplicationApartmentSteps.assertNextButtonIsEnabled(true);
        insuranceApplicationApartmentSteps.pressNextButton();
        insuranceApplicationApartmentSteps.assertNextButtonIsEnabled(false);
        insuranceApplicationApartmentSteps.inputLastName(VALID_NAME);
        insuranceApplicationApartmentSteps.inputFirstName(VALID_NAME);
        insuranceApplicationApartmentSteps.inputPatronymic(VALID_NAME);
        insuranceApplicationApartmentSteps.inputDateOfBirth(START_OF_CENTURY, APRIL, FORTH_DAY_OF_MONTH);
        insuranceApplicationApartmentSteps.inputPhoneNumber(PHONE_NUMBER);
        insuranceApplicationApartmentSteps.inputEmail(EMAIL);
        insuranceApplicationApartmentSteps.chooseDocumentType(DOCUMENT_TYPE_PASSPORT);
        insuranceApplicationApartmentSteps.inputPassportNumber(DOCUMENT_NUMBER);
        insuranceApplicationApartmentSteps.inputGotDocumentDate(TWENTY_EIGHTEEN, JULY, THIRTY_FIRST_DAY_OF_MONTH);
        insuranceApplicationApartmentSteps.inputDocumentDepartment(DOCUMENT_DEPARTMENT);
        insuranceApplicationApartmentSteps.scrollDown();
        insuranceApplicationApartmentSteps.inputState(STATE);
        insuranceApplicationApartmentSteps.inputCity(CITY);
        insuranceApplicationApartmentSteps.inputStreet(STREET);
        insuranceApplicationApartmentSteps.inputHouse(HOUSE);
        insuranceApplicationApartmentSteps.inputEntrance(ENTRANCE);
        insuranceApplicationApartmentSteps.inputApartment(APARTMENT);
        insuranceApplicationApartmentSteps.assertNextButtonIsEnabled(true);
        insuranceApplicationApartmentSteps.pressNextButton();
        insuranceApplicationApartmentSteps.assertConfirmButtonIsEnabled(false);
        insuranceApplicationApartmentSteps.inputYearOfConstruction(YEAR_OF_CONSTRUCTION);
        insuranceApplicationApartmentSteps.inputBuildingArea(APARTMENT_AREA);
        insuranceApplicationApartmentSteps.inputBuildingCost(APARTMENT_COST);
        insuranceApplicationApartmentSteps.inputInsuranceAmount(APARTMENT_INSURANCE_AMOUNT);
        insuranceApplicationApartmentSteps.inputBuildingRegion(STATE);
        insuranceApplicationApartmentSteps.inputBuildingCity(CITY);
        insuranceApplicationApartmentSteps.inputBuildingStreet(STREET);
        insuranceApplicationApartmentSteps.inputBuildingHouse(HOUSE);
        insuranceApplicationApartmentSteps.assertConfirmButtonIsEnabled(true);
        insuranceApplicationApartmentSteps.pressConfirmButton();
        insuranceApplicationApartmentSteps.assertSuccessImageIsPresent();
    }

    @DisplayName("Первый этап формы подачи заявки на квартиру : ввод валидных данных")
    @Description("Тест направлен на проверку валидации первого этапа" +
            " формы завки на страхование квартиры при валидных данных")
    @TmsLink("LIB5-2462")
    @ParameterizedTest
    @MethodSource("dataProviders.InsuranceFormDataProvider#formValidationFirstStepValidData")
    public void formValidationFirstStepValidDataTest(Currencies currency, String duration) {
        insuranceApplicationApartmentSteps.selectCurrency(currency);
        insuranceApplicationApartmentSteps.inputInsuranceDuration(duration);
        insuranceApplicationApartmentSteps.choseInsuranceStartingTomorrow();
        insuranceApplicationApartmentSteps.pressPayPartsSwitch();
        insuranceApplicationApartmentSteps.assertNextButtonIsEnabled(true);

    }
}
