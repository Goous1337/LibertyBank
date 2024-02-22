package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static web.helpers.Waiters.waitElement;

public class ChangePasswordPage extends BasePage {
    @FindBy(xpath = "//button[contains(text(),'Отмена')]")
    private WebElement cancelPasswordChangeBtn;
    @FindBy(xpath = "//h3[contains(text(),'Изменить пароль')]")
    private WebElement changePasswordPage;

    public void clickCancelPasswordChangeBtn() {
        waitElement(cancelPasswordChangeBtn).click();
    }

    public boolean isChangePasswordPresent() {
        return changePasswordPage.isDisplayed();
    }
}
