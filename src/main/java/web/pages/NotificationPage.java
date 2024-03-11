package web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationPage extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'notifications')]//p[1]")
    private WebElement notificationTextStatus;

    public boolean isNotificationStatusSuccess() {
        return notificationTextStatus.getText().equals("Успех");
    }
}
