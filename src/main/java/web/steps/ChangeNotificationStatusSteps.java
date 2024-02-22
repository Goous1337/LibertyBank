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
}
