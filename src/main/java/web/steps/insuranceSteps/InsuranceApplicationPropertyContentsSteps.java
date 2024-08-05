package web.steps.insuranceSteps;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import web.pages.insurancePages.property.InsuranceApplicationPropertyContentsPage;
import web.constans.insurance.InsuranceEnum.ContentsApplicationField;

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

    @Step("Ввести минимальную длительность страховки")
    public void inputInsuranceDuration(String duration) {
        insuranceApplicationPage.inputField(ContentsApplicationField.INSURANCE_DURATION, duration);
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

    @Step("Ввести название объекта страхования")
    public void inputThingName(String thingName) {
        insuranceApplicationPage.inputField(ContentsApplicationField.THING_NAME, thingName);
    }

    @Step("Выбрать тип объекта")
    public void selectThingType(Integer thingType) {
        insuranceApplicationPage.selectThingType(thingType);
    }

    @Step("Ввести стоимость объекта")
    public void inputThingCost(String thingCost) {
        insuranceApplicationPage.inputField(ContentsApplicationField.THING_COST, thingCost);
    }

    @Step("Выбрать материал постройки")
    public void selectConstructionType(Integer materialType) {
        insuranceApplicationPage.selectConstructionType(materialType);
    }

    @Step("Ввести город")
    public void inputCity(Integer city) {
        insuranceApplicationPage.inputCity(city);
    }

    @Step("Ввести улицу")
    public void inputStreet(String street) {
        insuranceApplicationPage.inputField(ContentsApplicationField.STREET, street);
    }

    @Step("Ввести дом")
    public void inputHouse(String house) {
        insuranceApplicationPage.inputField(ContentsApplicationField.BUILDING, house);
    }

    @Step("Ввести квартиру")
    public void inputApartment(String apartment) {
        insuranceApplicationPage.inputField(ContentsApplicationField.APARTMENT, apartment);
    }

    @Step("Ввести этажность")
    public void inputFloor(String floor) {
        insuranceApplicationPage.inputField(ContentsApplicationField.FLOOR, floor);
    }

    @Step("Ввести подъезд")
    public void inputEntrance(String entrance) {
        insuranceApplicationPage.inputField(ContentsApplicationField.ENTRANCE, entrance);
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
}
