package web.steps.insuranceSteps;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import web.constans.insurance.InsuranceEnum.Currencies;
import web.pages.insurancePages.property.InsuranceApplicationApartmentPage;
import web.constans.insurance.InsuranceEnum.ApartmentApplicationField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class InsuranceApplicationApartmentSteps {
    protected InsuranceApplicationApartmentPage insuranceApplicationPage;

    public InsuranceApplicationApartmentSteps() {
        insuranceApplicationPage = new InsuranceApplicationApartmentPage();
    }

    @Step("Проверить, что кнопка 'Дальше' активна")
    public void assertNextButtonIsEnabled(Boolean status) {
        String message = status ? "Кнопка 'Дальше' неактивна" : "Кнопка 'Дальше' активна";
        assertEquals(insuranceApplicationPage.nextButtonIsEnabled(), status, message);
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
    public void assertCurrencyRubChosen(Boolean status) {
        String message = status ? "Валюта RUB выбрана" : "Валюта RUB не выбрана";
        assertEquals(insuranceApplicationPage.isSelectedRub(), status, message);
    }

    @Step("Выбрать вылюту EUR")
    public void chooseCurrencyEur() {
        insuranceApplicationPage.clickRadioEur();
    }

    @Step("Проверить, что выбрана валюта EUR")
    public void assertCurrencyEurChosen(Boolean status) {
        String message = status ? "Валюта EUR выбрана" : "Валюта EUR не выбрана";
        assertEquals(insuranceApplicationPage.isSelectedEur(), status, message);
    }

    @Step("Выбрать вылюту USD")
    public void chooseCurrencyUsd() {
        insuranceApplicationPage.clickRadioUsd();
    }

    @Step("Проверить, что выбрана валюта USD")
    public void assertCurrencyUsdChosen(Boolean status) {
        String message = status ? "Валюта USD выбрана" : "Валюта USD не выбрана";
        assertEquals(insuranceApplicationPage.isSelectedUsd(), status, message);
    }

    @Step("Ввести длительность страховки")
    public void inputInsuranceDuration(String duration) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.INSURANCE_DURATION, duration);
    }

    @Step("Выбрать начало действия страховки с завтрашнего дня")
    public void choseInsuranceStartingTomorrow() {
        insuranceApplicationPage.selectStartingTomorrow();
    }

    @Step("Ввести имя")
    public void inputFirstName(String firstName) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.FIRST_NAME, firstName);
    }

    @Step("Ввести фамилию")
    public void inputLastName(String lastName) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.LAST_NAME, lastName);
    }

    @Step("Ввести отчество")
    public void inputPatronymic(String patronymic) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.PATRONYMIC, patronymic);
    }

    @Step("Выбрать тип документа")
    public void chooseDocumentType(Integer documentType) {
        insuranceApplicationPage.selectDocumentType(documentType);
    }

    @Step("Ввести номер паспорта")
    public void inputPassportNumber(String number) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.PASSPORT_NUMBER, number);
    }

    @Step("Ввести номер вида на жительство")
    public void inputResidenceNumber(String number) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.RESIDENCE_NUMBER, number);
    }

    @Step("Ввести номер свидетельства беженца")
    public void inputRefugeNumber(String number) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.REFUGE_NUMBER, number);
    }

    @Step("Ввести дату получения документа")
    public void inputGotDocumentDate(Integer year, Integer month, Integer day) {
        insuranceApplicationPage.inputDocumentGotDate(year, month, day);
    }

    @Step("Ввести дату рождения")
    public void inputDateOfBirth(Integer year, Integer month, Integer day) {
        insuranceApplicationPage.inputDateOfBirth(year, month, day);
    }

    @Step("Ввести номер телефона")
    public void inputPhoneNumber(String phoneNumber) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.PHONE, phoneNumber);
    }

    @Step("Ввести email")
    public void inputEmail(String email) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.EMAIL, email);
    }

    @Step("Ввести кем выдан документ")
    public void inputDocumentDepartment(String documentDepartment) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.ISSUED_BY, documentDepartment);
    }

    @Step("Ввести область")
    public void inputState(String state) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.REGION, state);
    }

    @Step("Ввести город")
    public void inputCity(String city) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.CITY, city);
    }

    @Step("Ввести улицу")
    public void inputStreet(String street) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.STREET, street);
    }

    @Step("Ввести дом")
    public void inputHouse(String house) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.BUILDING, house);
    }

    @Step("Ввести квартиру")
    public void inputApartment(String apartment) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.APARTMENT, apartment);
    }

    @Step("Ввести подъезд")
    public void inputEntrance(String entrance) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.ENTRANCE, entrance);
    }

    @Step("Ввести год постройки")
    public void inputYearOfConstruction(String year) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.CONSTRUCTION_YEAR, year);
    }

    @Step("Ввести площадь квартиры")
    public void inputBuildingArea(String area) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.BUILDING_SPACE, area);
    }

    @Step("Ввести актуальную стоимость квартиры")
    public void inputBuildingCost(String cost) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.ACTUAL_COST, cost);
    }

    @Step("Ввести страховую сумму")
    public void inputInsuranceAmount(String amount) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.INSURANCE_AMOUNT, amount);
    }

    @Step("Ввести область/регион строения")
    public void inputBuildingRegion(String region) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.BUILDING_REGION, region);
    }

    @Step("Ввести город строения")
    public void inputBuildingCity(String city) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.BUILDING_CITY, city);
    }

    @Step("Ввести улицу строения")
    public void inputBuildingStreet(String street) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.BUILDING_STREET, street);
    }

    @Step("Ввести здание строения")
    public void inputBuildingHouse(String house) {
        insuranceApplicationPage.inputField(ApartmentApplicationField.BUILDING_HOUSE, house);
    }

    @Step("Нажать кнопку 'Назад'")
    public void pressBackButton() {
        insuranceApplicationPage.pressBackButton();
    }

    @Step("Проверить, что отображается экран 'Успешно'")
    public void assertSuccessImageIsPresent() {
        try {
            assertTrue(insuranceApplicationPage.successImageIsPresent(), "Оформление не успешно");
        } catch (TimeoutException t) {
            assertFalse(insuranceApplicationPage.failImageIsPresent(), "Оформление не успешно");
        }
    }

    @Step("Проверить, что кнопка 'Оформить' активна")
    public void assertConfirmButtonIsEnabled(Boolean status) {
        String message = status ? "Кнопка 'Оформить' неактивна" : "Кнопка 'Оформить' активна";
        assertEquals(insuranceApplicationPage.confirmButtonIsEnabled(), status, message);
    }

    @Step("Нажать кнопку 'Оформить'")
    public void pressConfirmButton() {
        insuranceApplicationPage.pressConfirmButton();
    }

    @Step("Проскролить форму вниз")
    public void scrollDown() {
        insuranceApplicationPage.scrollDown();
    }

    @Step("Нажать на переключатель 'Оплата частями'")
    public void pressPayPartsSwitch() {
        insuranceApplicationPage.pressPayPartsSwitch();
    }

    public void selectCurrency(Currencies currency) {
        switch (currency) {
            case EUR:
                chooseCurrencyEur();
                assertCurrencyEurChosen(true);
                assertCurrencyRubChosen(false);
                assertCurrencyUsdChosen(false);
                break;
            case RUB:
                chooseCurrencyRub();
                assertCurrencyRubChosen(true);
                assertCurrencyEurChosen(false);
                assertCurrencyUsdChosen(false);
                break;
            case USD:
                chooseCurrencyUsd();
                assertCurrencyUsdChosen(true);
                assertCurrencyEurChosen(false);
                assertCurrencyRubChosen(false);
                break;
        }
    }
}
