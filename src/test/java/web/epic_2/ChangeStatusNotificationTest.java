package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static org.junit.jupiter.api.Assertions.*;
import static web.constans.AccountServiceConstants.DISPLAYED_MESSAGE;
import static web.constans.UrlConfig.CHANGE_NOTIFICATION_URL;

public class ChangeStatusNotificationTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CHANGE_NOTIFICATION_URL);
    }

    @DisplayName("Основной сценарий. Проверка изменения статуса настройки получения SMS-оповещения")
    @Description("В данном тест-кейсе проводится проверка возможности изменения настроек получения пользователем" +
            " SMS-оповещения в личном кабинете")
    @TmsLink("LIB-2495")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @Test
    public void changeStatusSMSNotification() {
        boolean beforeChanging = changeNotificationStatusSteps.isSelectedSMSNotificationCheckBox();
        boolean afterChanging = changeNotificationStatusSteps.clickSMSNotificationCheckbox()
                .isChangedSMSNotificationCheckBox(beforeChanging);
        assertTrue(beforeChanging != afterChanging, String.format(DISPLAYED_MESSAGE,
                "Checkbox SMS-оповещения не изменился"));
    }

    @DisplayName("Основной сценарий. Проверка изменения статуса настройки получения Email-оповещения")
    @Description("В данном тест-кейсе проводится проверка возможности изменения настроек получения пользователем" +
            " Email-оповещения в личном кабинете")
    @TmsLink("LIB-2492")
    @Tags({@Tag("Web"), @Tag("Smoke"), @Tag("Positive")})
    @Test
    public void changeStatusEmailNotification() {
        boolean beforeChanging = changeNotificationStatusSteps.isSelectedEmailNotificationCheckBox();
        boolean afterChanging = changeNotificationStatusSteps.clickEmailNotificationCheckbox()
                .isChangedEmailNotificationCheckBox(beforeChanging);
        assertTrue(beforeChanging != afterChanging, String.format(DISPLAYED_MESSAGE,
                "Checkbox Email-оповещения не изменился"));
    }
}
