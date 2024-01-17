package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Открытые счета')]")
    private WebElement openAccountsTab;

    @FindBy(xpath = "//*[contains(text(), 'Закрытые счета')]")
    private WebElement closedAccountsTab;

    @FindBy(xpath = "//*[contains(text(), 'Заблокированные счета')]")
    private WebElement blockedAccountsTab;

    @FindBy(xpath = "//*[contains(text(), 'Основной счет')]")
    private WebElement mainAccountLabel;

    public void clickMainAccountLabel() {
        mainAccountLabel.click();
    }
}
