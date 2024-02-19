package web.steps;

import io.qameta.allure.Step;
import web.pages.SecurityPage;

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
}
