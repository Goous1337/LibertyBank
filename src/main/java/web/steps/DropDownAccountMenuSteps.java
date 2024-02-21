package web.steps;

import io.qameta.allure.Step;
import web.pages.DropDownAccountMenuPage;

public class DropDownAccountMenuSteps {
    protected DropDownAccountMenuPage dropDownAccountMenuPage;

    public DropDownAccountMenuSteps() {
        dropDownAccountMenuPage = new DropDownAccountMenuPage();
    }

    @Step("Выполняется проверка видимости кнопок в выподающем меню личного кабинета")
    public void checkVisibilityOfUserPanelButtons(int time) {
        dropDownAccountMenuPage.checkVisibilityOfUserPanelButtons(time);
    }
}
