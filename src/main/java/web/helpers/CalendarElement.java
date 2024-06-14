package web.helpers;

import org.openqa.selenium.WebElement;

import static web.helpers.Waiters.waitElement;

public class CalendarElement {

    private int parseMonth(String monthStr) {
        return switch (monthStr) {
            case "Январь" -> 1;
            case "Февраль" -> 2;
            case "Март" -> 3;
            case "Апрель" -> 4;
            case "Май" -> 5;
            case "Июнь" -> 6;
            case "Июль" -> 7;
            case "Август" -> 8;
            case "Сентябрь" -> 9;
            case "Октябрь" -> 10;
            case "Ноябрь" -> 11;
            case "Декабрь" -> 12;
            default -> 0;
        };
    }

    public void switchMonth(WebElement navigationLabel, WebElement nextMonth, WebElement previousMonth, Integer month) {
        String labelText = waitElement(navigationLabel).getText();
        int spaceIndex = labelText.indexOf(" ");
        String labelMonth = labelText.substring(0, spaceIndex);
        int currentMonth = parseMonth(labelMonth);
        while (currentMonth != month) {
            if (currentMonth > month) waitElement(previousMonth).click();
            if (currentMonth < month) waitElement(nextMonth).click();
            labelText = waitElement(navigationLabel).getText();
            spaceIndex = labelText.indexOf(" ");
            labelMonth = labelText.substring(0, spaceIndex);
            currentMonth = parseMonth(labelMonth);
        }
    }

    public void switchYear(WebElement navigationLabel, WebElement nextYear, WebElement previousYear, Integer year) {
        String labelText = waitElement(navigationLabel).getText();
        int spaceIndex = labelText.indexOf(" ");
        String labelYear = labelText.substring(spaceIndex + 1, spaceIndex + 5);
        int currentYear = Integer.parseInt(labelYear);
        while (currentYear != year) {
            if (currentYear > year) waitElement(previousYear).click();
            if (currentYear < year) waitElement(nextYear).click();
            labelText = waitElement(navigationLabel).getText();
            spaceIndex = labelText.indexOf(" ");
            labelYear = labelText.substring(spaceIndex + 1, spaceIndex + 5);
            currentYear = Integer.parseInt(labelYear);
        }
    }
}
