package web.steps.insuranceSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import web.pages.insurancePages.InsuranceApplicationPropertyContentsPage;

import static web.constans.InsuranceServiceConstants.*;

public class InsuranceApplicationPropertyContentsSteps {
    protected InsuranceApplicationPropertyContentsPage insuranceApplicationPage;

    public InsuranceApplicationPropertyContentsSteps() {
        insuranceApplicationPage = new InsuranceApplicationPropertyContentsPage();
    }

    @Step("Проверить, что кнопка 'Дальше' неактивна")
    public boolean assertNextButtonDisabled() {
        return insuranceApplicationPage.nextButtonIsEnabled();
    }

    @Step("Проверить, что кнопка 'Дальше' активна")
    public void assertNextButtonEnabled() {
        Assertions.assertTrue(insuranceApplicationPage.nextButtonIsEnabled());
    }

    @Step("Нажать кнопку 'Дальше'")
    public void pressNextButton() {
        insuranceApplicationPage.pressNextButton();
    }

    @Step("Выбрать вылюту RUB")
    public void chooseCurrencyRub() {
        insuranceApplicationPage.clickRadioRub();
    }

    @Step("Проверить, что выбрана валюта RUB")
    public void assertCurrencyRubChosen() {
        Assertions.assertTrue(insuranceApplicationPage.isSelectedRub());
    }

    @Step("Проверить, что не выбрана валюта RUB")
    public void assertCurrencyRubNotChosen() {
        Assertions.assertFalse(insuranceApplicationPage.isSelectedRub());
    }

    @Step("Выбрать вылюту EUR")
    public void chooseCurrencyEur() {
        insuranceApplicationPage.clickRadioEur();
    }

    @Step("Проверить, что выбрана валюта EUR")
    public void assertCurrencyEurChosen() {
        Assertions.assertTrue(insuranceApplicationPage.isSelectedEur());
    }

    @Step("Проверить, что не выбрана валюта EUR")
    public void assertCurrencyEurNotChosen() {
        Assertions.assertFalse(insuranceApplicationPage.isSelectedEur());
    }

    @Step("Выбрать вылюту USD")
    public void chooseCurrencyUsd() {
        insuranceApplicationPage.clickRadioUsd();
    }

    @Step("Проверить, что выбрана валюта USD")
    public void assertCurrencyUsdChosen() {
        Assertions.assertTrue(insuranceApplicationPage.isSelectedUsd());
    }

    @Step("Проверить, что не выбрана валюта USD")
    public void assertCurrencyUsdNotChosen() {
        Assertions.assertFalse(insuranceApplicationPage.isSelectedUsd());
    }

    @Step("Ввести минимальную длительность страховки")
    public void inputMinimalInsuranceDuration() {
        insuranceApplicationPage.inputInsuranceDuration(INSURANCE_DURATION_MINIMUM);
    }

    @Step("Ввести максимальную длительность страховки")
    public void inputMaximalInsuranceDuration() {
        insuranceApplicationPage.inputInsuranceDuration(INSURANCE_DURATION_MAXIMUM);
    }

    @Step("Ввести невалидную длительность страховки")
    public void inputInvalidInsuranceDuration() {
        insuranceApplicationPage.inputInsuranceDuration(INSURANCE_DURATION_INVALID);
    }

    @Step("Выбрать начало действия страховки с завтрашнего дня")
    public void choseInsuranceStartingTomorrow() {
        insuranceApplicationPage.selectStartingTomorrow();
    }

    @Step("Проверить заголовок страницы")
    public void assertPageTitle() {
        Assertions.assertEquals(insuranceApplicationPage.getTitleText(),
                "Заявка на страхование домашнего имущества");
    }

    @Step("Ввести валидное имя")
    public void inputValidFirstName() {
        insuranceApplicationPage.inputFirstName(VALID_NAME);
    }

    @Step("Ввести невалидное имя")
    public void inputInvalidFirstName() {
        insuranceApplicationPage.inputFirstName(INVALID_NAME);
    }

    @Step("Ввести валидную фамилию")
    public void inputValidLastName() {
        insuranceApplicationPage.inputLastName(VALID_NAME);
    }

    @Step("Ввести невалидную фамилию")
    public void inputInvalidLastName() {
        insuranceApplicationPage.inputLastName(INVALID_NAME);
    }

    @Step("Ввести валидное отчество")
    public void inputValidPatronymic() {
        insuranceApplicationPage.inputPatronymic(VALID_NAME);
    }

    @Step("Ввести невалидное отчество")
    public void inputInvalidPatronymic() {
        insuranceApplicationPage.inputPatronymic(INVALID_NAME);
    }

    @Step("Выбрать тип документа - паспорт")
    public void chooseDocumentTypePassport() {
        insuranceApplicationPage.selectDocumentType(DOCUMENT_TYPE_PASSPORT);
    }

    @Step("Выбрать тип документа - вид на жительство")
    public void chooseDocumentTypeResidence() {
        insuranceApplicationPage.selectDocumentType(DOCUMENT_TYPE_RESIDENCE);
    }

    @Step("Выбрать тип документа - свидетельство беженца")
    public void chooseDocumentTypeRefuge() {
        insuranceApplicationPage.selectDocumentType(DOCUMENT_TYPE_REFUGE);
    }

    @Step("Ввести номер паспорта")
    public void inputPassportNumber() {
        insuranceApplicationPage.inputPassportNumber(DOCUMENT_NUMBER);
    }

    @Step("Ввести номер вида на жительство")
    public void inputResidenceNumber() {
        insuranceApplicationPage.inputResidenceNumber(DOCUMENT_NUMBER);
    }

    @Step("Ввести номер свидетельства беженца")
    public void inputRefugeNumber() {
        insuranceApplicationPage.inputRefugeNumber(DOCUMENT_NUMBER);
    }

    @Step("Ввести дату получения документа")
    public void inputGotDocumentDate() {
        insuranceApplicationPage.inputDocumentGotDate(TWENTY_EIGHTEEN, AUGUST, TWENTY_EIGHTH_DAY_OF_MONTH);
    }

    @Step("Ввести дату рождения")
    public void inputDateOfBirth() {
        insuranceApplicationPage.inputDateOfBirth(START_OF_CENTURY, AUGUST, FORTH_DAY_OF_MONTH);
    }

    @Step("Ввести номер телефона")
    public void inputPhoneNumber() {
        insuranceApplicationPage.inputPhone(PHONE_NUMBER);
    }

    @Step("Ввести email")
    public void inputEmail() {
        insuranceApplicationPage.inputEmail(EMAIL);
    }

    @Step("Ввести кем выдан документ")
    public void inputDocumentDepartment() {
        insuranceApplicationPage.inputIssuedBy(DOCUMENT_DEPARTMENT);
    }

    @Step("Ввести название объекта страхования")
    public void inputThingName() {
        insuranceApplicationPage.inputThingName(THING_NAME);
    }

    @Step("Выбрать тип объекта")
    public void selectThingType() {
        insuranceApplicationPage.selectThingType(THING_TYPE_FURNITURE);
    }

    @Step("Ввести стоимость объекта")
    public void inputThingCost() {
        insuranceApplicationPage.inputThingCost(THING_COST);
    }

    @Step("Выбрать материал постройки")
    public void selectConstructionType() {
        insuranceApplicationPage.selectConstructionType(CONSTRUCTION_TYPE_BRICK);
    }

    @Step("Ввести город")
    public void inputCity() {
        insuranceApplicationPage.inputCity(CITY);
    }

    @Step("Ввести улицу")
    public void inputStreet() {
        insuranceApplicationPage.inputStreet(STREET);
    }

    @Step("Ввести дом")
    public void inputHouse() {
        insuranceApplicationPage.inputHouse(HOUSE);
    }

    @Step("Ввести квартиру")
    public void inputApartment() {
        insuranceApplicationPage.inputApartment(APARTMENT);
    }

    @Step("Ввести этажность")
    public void inputFloor() {
        insuranceApplicationPage.inputFloor(FLOOR);
    }

    @Step("Ввести подъезд")
    public void inputEntrance() {
        insuranceApplicationPage.inputEntrance(ENTRANCE);
    }

    @Step("Нажать кнопку 'Назад'")
    public void pressBackButton() {
        insuranceApplicationPage.pressBackButton();
    }

    @Step("Проверить, что отображается экран 'Успешно'")
    public void assertSuccessImageIsPresent() {
        Assertions.assertTrue(insuranceApplicationPage.successImageIsPresent());
    }

    @Step("Проверить, что кнопка 'Оформить' активна")
    public void assertConfirmButtonEnabled() {
        Assertions.assertTrue(insuranceApplicationPage.confirmButtonIsEnabled());
    }

    @Step("Проверить, что кнопка 'Оформить'  неактивна")
    public void assertConfirmButtonDisabled() {
        Assertions.assertFalse(insuranceApplicationPage.confirmButtonIsEnabled());
    }

    @Step("Нажать кнопку 'Оформить'")
    public void pressConfirmButton() {
        insuranceApplicationPage.pressConfirmButton();
    }
}
