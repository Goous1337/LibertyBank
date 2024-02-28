package web.steps;

import io.qameta.allure.Step;
import web.pages.SecurityPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.AccountServiceConstants.DISPLAYED_MESSAGE;

public class SecuritySteps {
    protected SecurityPage securityPage;

    public SecuritySteps() {
        securityPage = new SecurityPage();
    }

    @Step("Кликнуть на кнопку 'Изменить'.")
    public void clickChangePasswordBtn() {
        securityPage.clickChangePasswordBtn();
    }

    @Step("Отображается надпись 'Безопасность'")
    public boolean isSecurityBarDisplayed() {
        return securityPage.isSecurityBarDisplayed();
    }

    @Step("Проверка отображения раздела 'Безопасность'")
    public void assertSecurityBarIsDisplayed() {
        assertTrue(isSecurityBarDisplayed(), String.format(DISPLAYED_MESSAGE,
                "Отображается раздел 'Безопасность"));
    }
}
