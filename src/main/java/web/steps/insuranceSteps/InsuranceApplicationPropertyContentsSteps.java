package web.steps.insuranceSteps;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import web.pages.insurancePages.InsuranceApplicationPropertyContentsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class InsuranceApplicationPropertyContentsSteps {
    protected InsuranceApplicationPropertyContentsPage insuranceApplicationPage;

    public InsuranceApplicationPropertyContentsSteps() {
        insuranceApplicationPage = new InsuranceApplicationPropertyContentsPage();
    }

    @Step("Проверить, что кнопка 'Дальше' активна")
    public void assertNextButtonIsEnabled(Boolean status) {
        String message;
        if (status) {
            message = "Кнопка 'Дальше' неактивна";
        } else {
            message = "Кнопка 'Дальше' активна";
        }
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
        String message;
        if (status) {
            message = "Валюта RUB выбрана";
        } else {
            message = "Валюта RUB не выбрана";
        }
        assertEquals(insuranceApplicationPage.isSelectedRub(), status, message);
    }

    @Step("Выбрать вылюту EUR")
    public void chooseCurrencyEur() {
        insuranceApplicationPage.clickRadioEur();
    }

    @Step("Проверить, что выбрана валюта EUR")
    public void assertCurrencyEurChosen(Boolean status) {
        String message;
        if (status) {
            message = "Валюта EUR выбрана";
        } else {
            message = "Валюта EUR не выбрана";
        }
        assertEquals(insuranceApplicationPage.isSelectedEur(), status, message);
    }

    @Step("Выбрать вылюту USD")
    public void chooseCurrencyUsd() {
        insuranceApplicationPage.clickRadioUsd();
    }

    @Step("Проверить, что выбрана валюта USD")
    public void assertCurrencyUsdChosen(Boolean status) {
        String message;
        if (status) {
            message = "Валюта USD выбрана";
        } else {
            message = "Валюта USD не выбрана";
        }
        assertEquals(insuranceApplicationPage.isSelectedUsd(), status, message);
    }

    @Step("Ввести минимальную длительность страховки")
    public void inputInsuranceDuration(String duration) {
        insuranceApplicationPage.inputInsuranceDuration(duration);
    }

    @Step("Выбрать начало действия страховки с завтрашнего дня")
    public void choseInsuranceStartingTomorrow() {
        insuranceApplicationPage.selectStartingTomorrow();
    }

    @Step("Проверить заголовок страницы")
    public void assertPageTitle() {
        assertEquals(insuranceApplicationPage.getTitleText(),
                "Заявка на страхование домашнего имущества", "Заголовок не совпадает с ожидаемым");
    }

    @Step("Ввести валидное имя")
    public void inputFirstName(String firstName) {
        insuranceApplicationPage.inputFirstName(firstName);
    }

    @Step("Ввести валидную фамилию")
    public void inputLastName(String lastName) {
        insuranceApplicationPage.inputLastName(lastName);
    }

    @Step("Ввести валидное отчество")
    public void inputPatronymic(String patronymic) {
        insuranceApplicationPage.inputPatronymic(patronymic);
    }

    @Step("Выбрать тип документа - паспорт")
    public void chooseDocumentType(Integer documentType) {
        insuranceApplicationPage.selectDocumentType(documentType);
    }

    @Step("Ввести номер паспорта")
    public void inputPassportNumber(String number) {
        insuranceApplicationPage.inputPassportNumber(number);
    }

    @Step("Ввести номер вида на жительство")
    public void inputResidenceNumber(String number) {
        insuranceApplicationPage.inputResidenceNumber(number);
    }

    @Step("Ввести номер свидетельства беженца")
    public void inputRefugeNumber(String number) {
        insuranceApplicationPage.inputRefugeNumber(number);
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
        insuranceApplicationPage.inputPhone(phoneNumber);
    }

    @Step("Ввести email")
    public void inputEmail(String email) {
        insuranceApplicationPage.inputEmail(email);
    }

    @Step("Ввести кем выдан документ")
    public void inputDocumentDepartment(String documentDepartment) {
        insuranceApplicationPage.inputIssuedBy(documentDepartment);
    }

    @Step("Ввести название объекта страхования")
    public void inputThingName(String thingName) {
        insuranceApplicationPage.inputThingName(thingName);
    }

    @Step("Выбрать тип объекта")
    public void selectThingType(Integer thingType) {
        insuranceApplicationPage.selectThingType(thingType);
    }

    @Step("Ввести стоимость объекта")
    public void inputThingCost(String thingCost) {
        insuranceApplicationPage.inputThingCost(thingCost);
    }

    @Step("Выбрать материал постройки")
    public void selectConstructionType(Integer materialType) {
        insuranceApplicationPage.selectConstructionType(materialType);
    }

    @Step("Ввести город")
    public void inputCity(String city) {
        insuranceApplicationPage.inputCity(city);
    }

    @Step("Ввести улицу")
    public void inputStreet(String street) {
        insuranceApplicationPage.inputStreet(street);
    }

    @Step("Ввести дом")
    public void inputHouse(String house) {
        insuranceApplicationPage.inputHouse(house);
    }

    @Step("Ввести квартиру")
    public void inputApartment(String apartment) {
        insuranceApplicationPage.inputApartment(apartment);
    }

    @Step("Ввести этажность")
    public void inputFloor(String floor) {
        insuranceApplicationPage.inputFloor(floor);
    }

    @Step("Ввести подъезд")
    public void inputEntrance(String entrance) {
        insuranceApplicationPage.inputEntrance(entrance);
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
        String message;
        if (status) {
            message = "Кнопка 'Оформить' неактивна";
        } else {
            message = "Кнопка 'Оформить' активна";
        }
        assertEquals(insuranceApplicationPage.confirmButtonIsEnabled(), status, message);
    }

    @Step("Нажать кнопку 'Оформить'")
    public void pressConfirmButton() {
        insuranceApplicationPage.pressConfirmButton();
    }
}
