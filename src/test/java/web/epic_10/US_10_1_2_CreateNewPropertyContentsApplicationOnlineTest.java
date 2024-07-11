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
import web.BaseTest;

import static web.constans.insurance.InsuranceServiceConstants.APARTMENT;
import static web.constans.insurance.InsuranceServiceConstants.CONSTRUCTION_TYPE_BRICK;
import static web.constans.insurance.InsuranceServiceConstants.ENTRANCE;
import static web.constans.insurance.InsuranceServiceConstants.FLOOR;
import static web.constans.insurance.InsuranceServiceConstants.HOUSE;
import static web.constans.insurance.InsuranceServiceConstants.INSURANCE_DURATION_MINIMUM;
import static web.constans.insurance.InsuranceServiceConstants.MOSCOW;
import static web.constans.insurance.InsuranceServiceConstants.STREET;
import static web.constans.insurance.InsuranceServiceConstants.THING_COST;
import static web.constans.insurance.InsuranceServiceConstants.THING_NAME;
import static web.constans.insurance.InsuranceServiceConstants.THING_TYPE_FURNITURE;
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
    @TmsLink("LIB5-2033")
    @Test
    public void successfulOnlinePropertyApplicationTest() {
        insuranceApplicationPropertyContentsSteps.assertPageTitle();
        insuranceApplicationPropertyContentsSteps.assertNextButtonIsEnabled(false);
        insuranceApplicationPropertyContentsSteps.assertCurrencyRubChosen(true);
        insuranceApplicationPropertyContentsSteps.assertCurrencyEurChosen(false);
        insuranceApplicationPropertyContentsSteps.assertCurrencyUsdChosen(false);
        insuranceApplicationPropertyContentsSteps.inputInsuranceDuration(INSURANCE_DURATION_MINIMUM);
        insuranceApplicationPropertyContentsSteps.choseInsuranceStartingTomorrow();
        insuranceApplicationPropertyContentsSteps.assertNextButtonIsEnabled(true);
        insuranceApplicationPropertyContentsSteps.pressNextButton();
        insuranceApplicationPropertyContentsSteps.assertNextButtonIsEnabled(false);
        insuranceApplicationPropertyContentsSteps.inputThingName(THING_NAME);
        insuranceApplicationPropertyContentsSteps.inputThingCost(THING_COST);
        insuranceApplicationPropertyContentsSteps.selectThingType(THING_TYPE_FURNITURE);
        insuranceApplicationPropertyContentsSteps.assertNextButtonIsEnabled(true);
        insuranceApplicationPropertyContentsSteps.pressNextButton();
        insuranceApplicationPropertyContentsSteps.assertConfirmButtonIsEnabled(false);
        insuranceApplicationPropertyContentsSteps.selectConstructionType(CONSTRUCTION_TYPE_BRICK);
        insuranceApplicationPropertyContentsSteps.inputCity(MOSCOW);
        insuranceApplicationPropertyContentsSteps.inputHouse(HOUSE);
        insuranceApplicationPropertyContentsSteps.inputFloor(FLOOR);
        insuranceApplicationPropertyContentsSteps.inputStreet(STREET);
        insuranceApplicationPropertyContentsSteps.inputApartment(APARTMENT);
        insuranceApplicationPropertyContentsSteps.inputEntrance(ENTRANCE);
        insuranceApplicationPropertyContentsSteps.assertConfirmButtonIsEnabled(true);
        insuranceApplicationPropertyContentsSteps.pressConfirmButton();
        insuranceApplicationPropertyContentsSteps.assertSuccessImageIsPresent();
    }
}
