package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.helpers.Waiters;

public class ChangeNotificationStatusPage extends BasePage {
    @FindBy(xpath = "//input[@name = 'sms']")
    private WebElement statusSMSNotificationCheckbox;
    @FindBy(xpath = "//li[contains(text(), 'SMS-оповещения')]//label")
    private WebElement statusSMSNotificationLabel;

    public void clickSMSNotificationCheckbox() {
        statusSMSNotificationLabel.click();
    }

    public boolean isSelectedSMSNotificationCheckBox() {
        return statusSMSNotificationCheckbox.isSelected();
    }

    public boolean isChangedSMSNotificationCheckBox(boolean status) {
        Waiters.waitCheckbox(!status, statusSMSNotificationCheckbox);
        return statusSMSNotificationCheckbox.isSelected();
    }


}
