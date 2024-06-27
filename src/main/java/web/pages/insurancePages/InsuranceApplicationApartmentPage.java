package web.pages.insurancePages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.drivers.DriverManager;
import web.helpers.CalendarElement;
import web.pages.BasePage;

import java.util.List;

import static web.helpers.Waiters.waitElement;

public class InsuranceApplicationApartmentPage extends BasePage {
    @FindBy(xpath = "//*[contains(text(),'Далее')]")
    private WebElement nextButton;
    @FindBy(xpath = "//*[contains(text(),'Оформить заявку')]")
    private WebElement confirmButton;
    @FindBy(xpath = "//*[@id='RUB']")
    private WebElement radioCurrencyRub;
    @FindBy(xpath = "//*[@id='USD']")
    private WebElement radioCurrencyUsd;
    @FindBy(xpath = "//*[@id='EUR']")
    private WebElement radioCurrencyEur;
    @FindBy(xpath = "//*[@name = 'duration']/../..")
    private WebElement durationInputBoarder;
    @FindBy(xpath = "//input[@name='duration']")
    private WebElement durationInput;
    @FindBy(xpath = "//*[@name ='startDate']/../following-sibling::*")
    private WebElement startingDateCalendarIcon;
    @FindBy(xpath = "//h3[contains(@class, ' _containerTitle_1pww5_25')]")
    private WebElement pageTitle;
    @FindBy(xpath = "//*[@name ='partsPayment']/following-sibling::label")
    private WebElement partsPaymentSwitch;
    @FindBy(xpath = "//*[contains(@name, 'lastName')]/../..")
    private WebElement lastNameBorder;
    @FindBy(xpath = "//*[contains(@name, 'lastName')]")
    private WebElement lastNameInput;
    @FindBy(xpath = "//*[contains(@name, 'firstName')]/../..")
    private WebElement firstNameBorder;
    @FindBy(xpath = "//*[contains(@name, 'firstName')]")
    private WebElement firstNameInput;
    @FindBy(xpath = "//*[contains(@name, 'patronymic')]/../..")
    private WebElement patronymicBorder;
    @FindBy(xpath = "//*[contains(@name, 'patronymic')]")
    private WebElement patronymicInput;
    @FindBy(xpath = "//*[@class='_selectBlock_1a54x_5']//*[@data-testid = 'icon-arrow-down-grey']")
    private WebElement documentTypeBorder;
    private List<WebElement> documentTypes;
    @FindBy(xpath = "//*[contains(@name, 'documentType')]/following::ul")
    private WebElement documentTypeList;
    @FindBy(xpath = "//*[contains(@name, 'passportNumber')]/../..")
    private WebElement passportBorder;
    @FindBy(xpath = "//*[contains(@name, 'passportNumber')]")
    private WebElement passportInput;
    @FindBy(xpath = "//*[contains(@name, 'refugeeCertificateNumber')]/../..")
    private WebElement refugeBorder;
    @FindBy(xpath = "//*[contains(@name, 'refugeeCertificateNumber')]")
    private WebElement refugeInput;
    @FindBy(xpath = "//*[contains(@name, 'residentCardNumber')]/../..")
    private WebElement residenceBorder;
    @FindBy(xpath = "//*[contains(@name, 'residentCardNumber')]")
    private WebElement residenceInput;
    @FindBy(xpath = "//label[contains(text(),'Дата выдачи')]/../following-sibling::*")
    private WebElement documentGotCalendarIcon;
    @FindBy(xpath = "//*[@name='dateOfBirth']/../following-sibling::*")
    private WebElement dateOfBirthIcon;
    @FindBy(xpath = "//*[contains(@name, 'issuedBy')]/../..")
    private WebElement issuedByBorder;
    @FindBy(xpath = "//*[contains(@name, 'issuedBy')]")
    private WebElement issuedByInput;
    @FindBy(xpath = "//*[contains(@name, 'phoneNumber')]/../..")
    private WebElement phoneNumberBorder;
    @FindBy(xpath = "//*[contains(@name, 'phoneNumber')]")
    private WebElement phoneNumberInput;
    @FindBy(xpath = "//*[contains(@name, 'email')]/../..")
    private WebElement emailBorder;
    @FindBy(xpath = "//*[contains(@name, 'email')]")
    private WebElement emailInput;
    @FindBy(xpath = "//*[contains(@name, 'region')]/../..")
    private WebElement stateBorder;
    @FindBy(xpath = "//*[contains(@name, 'region')]")
    private WebElement stateInput;
    @FindBy(xpath = "//*[contains(@name, 'city')]/../..")
    private WebElement cityBorder;
    @FindBy(xpath = "//*[contains(@name, 'city')]")
    private WebElement cityInput;
    @FindBy(xpath = "//*[contains(@name, 'street')]/../..")
    private WebElement streetBorder;
    @FindBy(xpath = "//*[contains(@name, 'street')]")
    private WebElement streetInput;
    @FindBy(xpath = "//*[contains(@name, 'house')]/../..")
    private WebElement buildingBorder;
    @FindBy(xpath = "//*[contains(@name, 'house')]")
    private WebElement buildingInput;
    @FindBy(xpath = "//*[contains(@name, 'entrance')]/../..")
    private WebElement entranceBorder;
    @FindBy(xpath = "//*[contains(@name, 'entrance')]")
    private WebElement entranceInput;
    @FindBy(xpath = "//*[contains(@name, 'apartament')]/../..")
    private WebElement apartmentBorder;
    @FindBy(xpath = "//*[contains(@name, 'apartament')]")
    private WebElement apartmentInput;
    @FindBy(xpath = "//*[contains(@name, 'yearOfConstruction')]/../..")
    private WebElement yearOfConstructionBorder;
    @FindBy(xpath = "//*[contains(@name, 'yearOfConstruction')]")
    private WebElement yearOfConstructionInput;
    @FindBy(xpath = "//*[contains(@name, 'buildingArea')]/../..")
    private WebElement buildingAreaBorder;
    @FindBy(xpath = "//*[contains(@name, 'buildingArea')]")
    private WebElement buildingAreaInput;
    @FindBy(xpath = "//*[contains(@name, 'actualCost')]/../..")
    private WebElement actualCostBorder;
    @FindBy(xpath = "//*[contains(@name, 'actualCost')]")
    private WebElement actualCostInput;
    @FindBy(xpath = "//*[contains(@name, 'insuranceAmount')]/../..")
    private WebElement insuranceAmountBorder;
    @FindBy(xpath = "//*[contains(@name, 'insuranceAmount')]")
    private WebElement insuranceAmountInput;
    @FindBy(xpath = "//*[@name = 'regionOfBuilding']/../..")
    private WebElement regionOfBuildingBorder;
    @FindBy(xpath = "//*[@name = 'regionOfBuilding']")
    private WebElement regionOfBuildingInput;
    @FindBy(xpath = "//*[@name = 'cityOfBuilding']/../..")
    private WebElement cityOfBuildingBorder;
    @FindBy(xpath = "//*[@name = 'cityOfBuilding']")
    private WebElement cityOfBuildingInput;
    @FindBy(xpath = "//*[@name = 'streetOfBuilding']/../..")
    private WebElement streetOfBuildingBorder;
    @FindBy(xpath = "//*[@name = 'streetOfBuilding']")
    private WebElement streetOfBuildingInput;
    @FindBy(xpath = "//*[@name = 'houseOfBuilding']/../..")
    private WebElement houseOfBuildingBorder;
    @FindBy(xpath = "//*[@name = 'houseOfBuilding']")
    private WebElement houseOfBuildingInput;
    @FindBy(xpath = "//button[contains(@class,'_back-btn_c17ux_167')]")
    private WebElement backButton;
    @FindBy(xpath = "//*[@class='react-calendar _content__total_calendar_1r0ah_1 _calendar_1r0ah_121']")
    private WebElement calendarForm;
    @FindBy(xpath = "//*[@class='_fail-buttons_1gvr6_26']")
    private WebElement failImage;
    @FindBy(xpath = "//*[contains(text(),'Заявка успешно отправлена!')]")
    private WebElement successImage;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__arrow react-calendar__navigation__next-button']")
    private WebElement nextMonth;
    @FindBy(xpath = "//*[contains(@class,'react-calendar__tile--now')]/following-sibling::button[1]")
    private WebElement tomorrowButton;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__label']")
    private WebElement navigationLabel;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__arrow react-calendar__navigation__prev2-button']")
    private WebElement previousYear;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__arrow react-calendar__navigation__next2-button']")
    private WebElement nextYear;
    @FindBy(xpath = "//*[@class='react-calendar__navigation__arrow react-calendar__navigation__prev-button']")
    private WebElement previousMonth;
    private List<WebElement> dayButton;

    public boolean successImageIsPresent() {
        return waitElement(successImage).isDisplayed();
    }

    public boolean failImageIsPresent() {
        return waitElement(failImage).isDisplayed();
    }

    public boolean confirmButtonIsEnabled() {
        return waitElement(confirmButton).isEnabled();
    }

    public void pressConfirmButton() {
        waitElement(confirmButton).click();
    }

    public boolean nextButtonIsEnabled() {
        return waitElement(nextButton).isEnabled();
    }

    public void pressNextButton() {
        waitElement(nextButton).click();
    }

    public void clickRadioRub() {
        waitElement(radioCurrencyRub).click();
    }

    public boolean isSelectedRub() {
        return waitElement(radioCurrencyRub).isSelected();
    }

    public void clickRadioEur() {
        waitElement(radioCurrencyEur).click();
    }

    public boolean isSelectedEur() {
        return waitElement(radioCurrencyEur).isSelected();
    }

    public void clickRadioUsd() {
        waitElement(radioCurrencyUsd).click();
    }

    public boolean isSelectedUsd() {
        return waitElement(radioCurrencyUsd).isSelected();
    }

    public void inputInsuranceDuration(String duration) {
        waitElement(durationInputBoarder).click();
        waitElement(durationInput).sendKeys(duration);
    }

    public void selectStartingTomorrow() {
        waitElement(startingDateCalendarIcon).click();
        waitElement(calendarForm);
        try {
            waitElement(tomorrowButton).click();
        } catch (NoSuchElementException e) {
            waitElement(nextMonth).click();
            dayButton = DriverManager.getDriver().findElements(By
                    .xpath("//*[@class='react-calendar__month-view__days']/button"));
            dayButton.get(0).click();
        }

    }

    public String getTitleText() {
        return pageTitle.getText();
    }

    public void inputLastName(String lastName) {
        waitElement(lastNameBorder).click();
        waitElement(lastNameInput).sendKeys(lastName);
    }

    public void inputFirstName(String firstName) {
        waitElement(firstNameBorder).click();
        waitElement(firstNameInput).sendKeys(firstName);
    }

    public void inputPatronymic(String patronymic) {
        waitElement(patronymicBorder).click();
        waitElement(patronymicInput).sendKeys(patronymic);
    }

    public void selectDocumentType(Integer type) {
        waitElement(documentTypeBorder).click();
        waitElement(documentTypeList).isDisplayed();
        documentTypes = DriverManager.getDriver().findElements(By
                .xpath("//*[contains(@name, 'documentType')]/following::ul/li"));
        documentTypes.get(type).click();
    }

    public void inputPassportNumber(String number) {
        waitElement(passportBorder).click();
        waitElement(passportInput).sendKeys(number);
    }

    public void inputResidenceNumber(String number) {
        waitElement(residenceBorder).click();
        waitElement(residenceInput).sendKeys(number);
    }

    public void inputRefugeNumber(String number) {
        waitElement(refugeBorder).click();
        waitElement(refugeInput).sendKeys(number);
    }

    public void inputDocumentGotDate(Integer year, Integer month, Integer day) {
        waitElement(documentGotCalendarIcon).click();
        waitElement(calendarForm);
        CalendarElement calendarElement = new CalendarElement();
        calendarElement.switchMonth(navigationLabel, nextMonth, previousMonth, month);
        calendarElement.switchYear(navigationLabel, nextYear, previousYear, year);
        dayButton = DriverManager.getDriver().findElements(By
                .xpath("//*[@class='react-calendar__month-view__days']/button"));
        dayButton.get(day).click();
    }

    public void inputDateOfBirth(Integer year, Integer month, Integer day) {
        waitElement(dateOfBirthIcon).click();
        waitElement(calendarForm);
        CalendarElement calendarElement = new CalendarElement();
        calendarElement.switchMonth(navigationLabel, nextMonth, previousMonth, month);
        calendarElement.switchYear(navigationLabel, nextYear, previousYear, year);
        dayButton = DriverManager.getDriver().findElements(By
                .xpath("//*[@class='react-calendar__month-view__days']/button"));
        dayButton.get(day).click();
    }

    public void inputIssuedBy(String org) {
        waitElement(issuedByBorder).click();
        waitElement(issuedByInput).sendKeys(org);
    }

    public void inputPhone(String phoneNumber) {
        waitElement(phoneNumberBorder).click();
        waitElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void inputEmail(String email) {
        waitElement(emailBorder).click();
        waitElement(emailInput).sendKeys(email);
    }

    public void scrollDown() {
        scrollToElement(nextButton);
    }

    public void inputState(String state) {
        inputIntoSuggestionField(stateBorder, state, 4, 40);
    }

    public void inputCity(String city) {
        inputIntoSuggestionField(cityBorder, city, 4, 40);
    }

    public void inputStreet(String street) {
        inputIntoSuggestionField(streetBorder, street, 4, 40);
    }

    public void inputBuilding(String building) {
        waitElement(buildingBorder).click();
        waitElement(buildingInput).sendKeys(building);
    }

    public void inputApartment(String apartment) {
        waitElement(apartmentBorder).click();
        waitElement(apartmentInput).sendKeys(apartment);
    }

    public void inputEntrance(String entrance) {
        waitElement(entranceBorder).click();
        waitElement(entranceInput).sendKeys(entrance);
    }

    public void inputYearOfConstruction(String year) {
        waitElement(yearOfConstructionBorder).click();
        waitElement(yearOfConstructionInput).sendKeys(year);
    }

    public void inputBuildingArea(String area) {
        waitElement(buildingAreaBorder).click();
        waitElement(buildingAreaInput).sendKeys(area);
    }

    public void inputActualCost(String cost) {
        waitElement(actualCostBorder).click();
        waitElement(actualCostInput).sendKeys(cost);
    }

    public void inputInsuranceAmount(String amount) {
        waitElement(insuranceAmountBorder).click();
        waitElement(insuranceAmountInput).sendKeys(amount);
    }

    public void inputRegionOfBuilding(String region) {
        waitElement(regionOfBuildingBorder).click();
        waitElement(regionOfBuildingInput).sendKeys(region);
    }

    public void inputCityOfBuilding(String city) {
        waitElement(cityOfBuildingBorder).click();
        waitElement(cityOfBuildingInput).sendKeys(city);
    }

    public void inputStreetOfBuilding(String street) {
        waitElement(streetOfBuildingBorder).click();
        waitElement(streetOfBuildingInput).sendKeys(street);
    }

    public void inputHouseOfBuilding(String house) {
        waitElement(houseOfBuildingBorder).click();
        waitElement(houseOfBuildingInput).sendKeys(house);
    }

    public void pressBackButton() {
        waitElement(backButton).click();
    }
}
