package web.elements;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class BaseElement {

    protected WebElement element;

    public BaseElement(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("WebElement cannot be null");
        }
        this.element = element;
    }

    public boolean isDisplayed() {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void click() {
        element.click();
    }
}
