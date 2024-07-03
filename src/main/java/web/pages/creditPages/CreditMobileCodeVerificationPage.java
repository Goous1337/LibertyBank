package web.pages.creditPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.pages.BasePage;

import static web.helpers.Waiters.waitElement;

public class CreditMobileCodeVerificationPage extends BasePage {
    @FindBy(xpath = "//*[@data-testid = 'inputBase-1']")
    private WebElement firstInputForCode;
    @FindBy(xpath = "//*[@data-testid = 'inputBase-2']")
    private WebElement secondInputForCode;
    @FindBy(xpath = "//*[@data-testid = 'inputBase-3']")
    private WebElement thirdInputForCode;
    @FindBy(xpath = "//*[@data-testid = 'inputBase-4']")
    private WebElement forthInputForCode;
    @FindBy(xpath = "//*[@data-testid = 'inputBase-5']")
    private WebElement fivesInputForCode;
    @FindBy(xpath = "//*[@data-testid = 'inputBase-6']")
    private WebElement sixInputForCode;
    @FindBy(xpath = "//button[contains(text(), 'Далее')]")
    private WebElement nextButton;

    public void sendCode(String code) {
        waitElement(firstInputForCode);
        char[] chars = code.toCharArray();
        firstInputForCode.sendKeys(String.valueOf(chars[0]));
        secondInputForCode.sendKeys(String.valueOf(chars[1]));
        thirdInputForCode.sendKeys(String.valueOf(chars[2]));
        forthInputForCode.sendKeys(String.valueOf(chars[3]));
        fivesInputForCode.sendKeys(String.valueOf(chars[4]));
        sixInputForCode.sendKeys(String.valueOf(chars[5]));

    }

    public void clickNextButton() {
        waitElement(nextButton);
        nextButton.click();
    }
}
