package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationPage extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'notifications')]//*[name()='svg']")
    private WebElement notificationStatus;

    public boolean isNotificationStatusSuccess() {
        return notificationStatus.getAttribute("name").equals("success");
    }
}
