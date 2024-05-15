package web.epic_2;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;
import web.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.DISPLAYED_MESSAGE;
import static web.constans.AccountServiceConstants.NOT_EQUALS_MESSAGE;
import static web.constans.UrlConfig.CHANGE_NOTIFICATION_URL;

@Tags({@Tag("Web"), @Tag("2.0")})
@Epic("2 - Личный кабинет/Основное меню")
@DisplayName("US-2.2.4 [web] Уведомления")
public class US_2_2_4_ChangeStatusNotificationTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CHANGE_NOTIFICATION_URL);
    }

    @DisplayName("Основной сценарий. Проверка изменения статуса настройки получения SMS-оповещения")
    @Description("В данном тест-кейсе проводится проверка возможности изменения настроек получения пользователем" +
            " SMS-оповещения в личном кабинете")
    @TmsLink("LIB-2495")
    @Test
    public void changeStatusSMSNotification() {
        boolean beforeChanging = changeNotificationStatusSteps.isSelectedSMSNotificationCheckBox();
        boolean afterChanging = changeNotificationStatusSteps.clickSMSNotificationCheckbox()
                .isChangedSMSNotificationCheckBox(beforeChanging);
        assertTrue(beforeChanging != afterChanging, String.format(NOT_EQUALS_MESSAGE,
                "Checkbox"));
    }

    @DisplayName("Основной сценарий. Проверка изменения статуса настройки получения Email-оповещения")
    @Description("В данном тест-кейсе проводится проверка возможности изменения настроек получения пользователем" +
            " Email-оповещения в личном кабинете")
    @TmsLink("LIB-2492")
    @Test
    public void changeStatusEmailNotification() {
        boolean beforeChanging = changeNotificationStatusSteps.isSelectedEmailNotificationCheckBox();
        boolean afterChanging = changeNotificationStatusSteps.clickEmailNotificationCheckbox()
                .isChangedEmailNotificationCheckBox(beforeChanging);
        assertTrue(beforeChanging != afterChanging, String.format(NOT_EQUALS_MESSAGE,
                "Checkbox"));
    }

    @DisplayName("Основной сценарий. Проверка изменения статуса настройки получения Push-оповещения")
    @Description("В данном тест-кейсе проводится проверка возможности изменения настроек получения пользователем" +
            " Push-оповещения в личном кабинете")
    @TmsLink("LIB-2496")
    @Test
    public void changeStatusPushNotification() {
        boolean beforeChanging = changeNotificationStatusSteps.isSelectedPushNotificationCheckBox();
        boolean afterChanging = changeNotificationStatusSteps.clickPushNotificationCheckbox()
                .isChangedPushNotificationCheckBox(beforeChanging);
        assertTrue(beforeChanging != afterChanging, String.format(DISPLAYED_MESSAGE,
                "Checkbox Push-оповещения не изменился"));
    }
}
