package web.epic_5;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.BaseTest;

import static web.constans.UrlConfig.CARD_PRODUCTS_URL;

@Tag("Web")
@Epic("5 - Карты")
@Feature("US - 5.1.ALL Просмотр карточных продуктов")
@DisplayName("US - 5.1.ALL Просмотр карточных продуктов")
public class CardProductsInfoTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        open(CARD_PRODUCTS_URL);
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-2938")
    @DisplayName("Проверка отображения необходимой информации о каждой карте")
    @Description("Отображаются имена, сроки действия, стоимость обслуживания и валюта каждой карты")
    public void allCardsInformationTest() {
        cardProductsSteps.assertAllCardTitlesAreDisplayed();
        cardProductsSteps.assertAllCardValidityIsDisplayed();
        cardProductsSteps.assertAllCardServiceCostIsDisplayed();
        cardProductsSteps.assertAllCardCurrenciesAreDisplayed();
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-2938")
    @DisplayName("Просмотр страницы карточных продуктов, остортированной по 'RUB'")
    @Description("Отображаются только карты с валютой 'RUB'")
    public void cardProductsPageSortedByRubTest() {
        cardProductsSteps.assertAllCardsAreDisplayed();
        cardProductsSteps.assertAllCardTitlesAreDisplayed();
        cardProductsSteps.sortByRub();
        cardProductsSteps.assertClassicCardIsDisplayed();
        cardProductsSteps.assertChildCardIsDisplayed();
        cardProductsSteps.assertGoldCardIsDisplayed();
        cardProductsSteps.assertPlatinumCardIsDisplayed();
        cardProductsSteps.assertVirtualCardIsDisplayed();
        cardProductsSteps.assertSecureCardIsDisplayed();
        cardProductsSteps.assertTravelCardIsNotDisplayed();
    }

    @Test
    @Tag("Web")
    @TmsLink("LIB2-2938")
    @DisplayName("Просмотр страницы карточных продуктов, остортированной по 'USD'")
    @Description("Отображаются только карты с валютой 'USD'")
    public void cardProductsPageSortedByUsdTest() {
        cardProductsSteps.assertAllCardsAreDisplayed();
        cardProductsSteps.sortByUsd();
        cardProductsSteps.assertGoldCardIsDisplayed();
        cardProductsSteps.assertPlatinumCardIsDisplayed();
        cardProductsSteps.assertVirtualCardIsDisplayed();
        cardProductsSteps.assertSecureCardIsDisplayed();
        cardProductsSteps.assertTravelCardIsDisplayed();
        cardProductsSteps.assertClassicCardIsNotDisplayed();
        cardProductsSteps.assertChildCardIsNotDisplayed();
    }
}
