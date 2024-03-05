package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static web.helpers.Waiters.waitElement;

public class CloseCardPage extends BasePage {
    @FindBy(xpath = "//button[@class = '_btn_anzn6_1 _theme_primary_anzn6_44 _btn-size_m_anzn6_23 _btn-width_auto_anzn6_37 _step-button_ccacl_37'][text() = 'Подтвердить']")
    private WebElement confirmButton;
    @FindBy(xpath = "//input[@data-index = '0']")
    private WebElement placeholderInput;
    @FindBy(xpath = "////div[@class = '_inputWrapper_5n0km_1 _focus_5n0km_14 _m_5n0km_23 _enterSms-code_ccacl_43']//input[@data-index = '0']")
    private WebElement placeholderInputIt;

    public void clickConfirmCloseCardButton() {
        confirmButton.click();
    }

    public void clickPlaceholderInput() {
        waitElement(placeholderInput);
        placeholderInput.click();
    }

    public void enterPlaceholderInput(String password) {
        placeholderInput.sendKeys(password);
    }

    public boolean confirmButtonIsDisplayed() {
        return confirmButton.isDisplayed();
    }
}
