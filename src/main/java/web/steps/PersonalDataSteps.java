package web.steps;

import io.qameta.allure.Step;
import web.pages.NotificationPage;
import web.pages.PersonalDataPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.STATUS_ERROR_MESSAGE;

public class PersonalDataSteps {
    protected PersonalDataPage personalDataPage;
    protected NotificationPage notificationPage;

    public PersonalDataSteps() {
        personalDataPage = new PersonalDataPage();
        notificationPage = new NotificationPage();
    }

    @Step("Кликнуть на кнопку 'Изменить'")
    public void clickChangeEmailBtn() {
        personalDataPage.clickChangeEmailBtn();
    }

    @Step("Получить email до изменения")
    public String getOldEmailFromInput() {
        return personalDataPage.getEmailFromInput();
    }

    @Step("Проверка статуса изменения 'success'")
    public void assertLastNotificationStatus() {
        assertTrue(notificationPage.isNotificationStatusSuccess(), String.format(STATUS_ERROR_MESSAGE,
                "Success"));
    }
}
