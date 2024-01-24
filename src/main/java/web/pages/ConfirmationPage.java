package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(), 'Да')]")
    private WebElement confirmButton;

    @FindBy(xpath = "//button[contains(text(), 'Нет')]")
    private WebElement denyButton;

    public void clickConfirmButton() {
        confirmButton.click();
    }

    public void clickDenyButton() {
        denyButton.click();
    }
}
