package web.steps;

import io.qameta.allure.Step;
import web.pages.ChangeNotificationStatusPage;

public class ChangeNotificationStatusSteps {
    protected ChangeNotificationStatusPage changeNotificationStatusPage;

    public ChangeNotificationStatusSteps() {
        changeNotificationStatusPage = new ChangeNotificationStatusPage();
    }

    @Step("Кликнуть по checkbox SMS-оповещения")
    public ChangeNotificationStatusSteps clickSMSNotificationCheckbox() {
        changeNotificationStatusPage.clickSMSNotificationCheckbox();
        return this;
    }

    @Step("Проверка изменился ли checkbox SMS-оповещения  Проверка статуса checkbox SMS-оповещения")
    public boolean isSelectedSMSNotificationCheckBox() {
        return changeNotificationStatusPage.isSelectedSMSNotificationCheckBox();
    }

    @Step("Проверка изменился ли checkbox SMS-оповещения")
    public boolean isChangedSMSNotificationCheckBox(boolean status) {
        return changeNotificationStatusPage.isChangedSMSNotificationCheckBox(status);
    }

    @Step("Кликнуть по checkbox Email-оповещения")
    public ChangeNotificationStatusSteps clickEmailNotificationCheckbox() {
        changeNotificationStatusPage.clickEmailNotificationCheckbox();
        return this;
    }

    @Step("Проверка изменился ли checkbox Email-оповещения  Проверка статуса checkbox Email-оповещения")
    public boolean isSelectedEmailNotificationCheckBox() {
        return changeNotificationStatusPage.isSelectedEmailNotificationCheckBox();
    }

    @Step("Проверка изменился ли checkbox Email-оповещения")
    public boolean isChangedEmailNotificationCheckBox(boolean status) {
        return changeNotificationStatusPage.isChangedEmailNotificationCheckBox(status);
    }

    @Step("Кликнуть по checkbox Push-оповещения")
    public ChangeNotificationStatusSteps clickPushNotificationCheckbox() {
        changeNotificationStatusPage.clickPushNotificationCheckbox();
        return this;
    }

    @Step("Проверка изменился ли checkbox Push-оповещения  Проверка статуса checkbox Email-оповещения")
    public boolean isSelectedPushNotificationCheckBox() {
        return changeNotificationStatusPage.isSelectedPushNotificationCheckBox();
    }

    @Step("Проверка изменился ли checkbox Push-оповещения")
    public boolean isChangedPushNotificationCheckBox(boolean status) {
        return changeNotificationStatusPage.isChangedPushNotificationCheckBox(status);
    }
}
