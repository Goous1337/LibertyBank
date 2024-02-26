package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static web.helpers.Waiters.waitCheckbox;

public class ChangeNotificationStatusPage extends BasePage {
    @FindBy(xpath = "//input[@name = 'sms']")
    private WebElement statusSMSNotificationCheckbox;
    @FindBy(xpath = "//li[contains(text(), 'SMS-оповещения')]//label")
    private WebElement statusSMSNotificationLabel;
    @FindBy(xpath = "//input[@name=\"email\"]")
    private WebElement statusEmailNotificationCheckbox;
    @FindBy(xpath = "//li[contains(text(), 'Email-оповещения')]//label")
    private WebElement statusEmailNotificationLabel;


    public void clickSMSNotificationCheckbox() {
        statusSMSNotificationLabel.click();
    }

    public boolean isSelectedSMSNotificationCheckBox() {
        return statusSMSNotificationCheckbox.isSelected();
    }

    public boolean isChangedSMSNotificationCheckBox(boolean status) {
        return waitCheckbox(!status, statusSMSNotificationCheckbox).isSelected();
    }

    public void clickEmailNotificationCheckbox() {
        statusEmailNotificationLabel.click();
    }

    public boolean isSelectedEmailNotificationCheckBox() {
        return statusEmailNotificationCheckbox.isSelected();
    }

    public boolean isChangedEmailNotificationCheckBox(boolean status) {
        return waitCheckbox(!status, statusEmailNotificationCheckbox).isSelected();
    }
}
