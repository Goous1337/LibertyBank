package web.steps;

import io.qameta.allure.Step;
import web.pages.PersonalDataPage;

public class PersonalDataSteps {
    protected PersonalDataPage personalDataPage;

    public PersonalDataSteps() {
        personalDataPage = new PersonalDataPage();
    }

    @Step("Кликнуть на кнопку 'Изменить'")
    public void clickChangePasswordBtn() {
        personalDataPage.clickChangeEmailBtn();
    }

    @Step("Получить email до изменения")
    public String getOldEmailFromInput() {
        return personalDataPage.getEmailFromInput();
    }
}
