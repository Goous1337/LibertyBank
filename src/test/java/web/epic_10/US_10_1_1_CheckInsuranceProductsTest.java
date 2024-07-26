package web.epic_10;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import web.BaseTest;
import web.constans.insurance.InsuranceEnum.CarouselController;
import web.constans.insurance.InsuranceEnum.CarouselProducts;
import web.constans.insurance.InsuranceEnum.InsuranceProducts;
import web.constans.insurance.InsuranceEnum.InsuranceType;

import static org.junit.jupiter.api.Assertions.assertAll;
import static web.constans.UrlConfig.INSURANCE_PRODUCTS_URL;


@Tags({@Tag("Web"), @Tag("MVP")})
@Epic("10 - Страхование")
@Feature("US-10.1.1 Просмотр доступных видов страхования")
@DisplayName("US-10.1.1 Просмотр доступных видов страхования")
public class US_10_1_1_CheckInsuranceProductsTest extends BaseTest {
    @BeforeEach
    public void setUpTest() {
        authorization();
        open(INSURANCE_PRODUCTS_URL);
        checkInsuranceProductsSteps.setProductTypesWindowUrl();
    }

    @DisplayName("Проверка элементов страницы 'Страховые продукты банка'")
    @Description("Тест направлен на проверку категорий страхования на странице 'Страховые продукты банка'")
    @TmsLink("LIB5-3934")
    @Test
    public void checkInsuranceProductTypesTest() {
        assertAll(
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryPresent(InsuranceType.DMS),
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryPresent(InsuranceType.CARS),
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryPresent(InsuranceType.ACCIDENT),
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryPresent(InsuranceType.PROPERTY),
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryPresent(InsuranceType.TRAVEL)
        );
        assertAll(
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryText(InsuranceType.DMS),
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryText(InsuranceType.CARS),
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryText(InsuranceType.PROPERTY),
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryText(InsuranceType.ACCIDENT),
                () -> checkInsuranceProductsSteps.assertInsuranceCategoryText(InsuranceType.TRAVEL)
        );
    }

    @DisplayName("Проверка карусели на странице 'Страховые продукты банка'")
    @Description("Тест направлен на проверку карусели продуктов страхования на странице 'Страховые продукты банка'")
    @TmsLink("LIB5-3935")
    @Test
    public void checkInsuranceProductsCarouselTest() {
        checkInsuranceProductsSteps.assertCarouselControllerVisible(CarouselController.PREV, false);
        checkInsuranceProductsSteps.assertCarouselControllerVisible(CarouselController.NEXT, true);
        checkInsuranceProductsSteps.assertCarouselContainerText(CarouselProducts.KASKO);
        checkInsuranceProductsSteps.assertCarouselContainerText(CarouselProducts.ACCIDENT);
        checkInsuranceProductsSteps.pressCarouselController(CarouselController.NEXT);
        checkInsuranceProductsSteps.assertCarouselControllerVisible(CarouselController.PREV, true);
        checkInsuranceProductsSteps.assertCarouselControllerVisible(CarouselController.NEXT, true);
        checkInsuranceProductsSteps.assertCarouselContainerText(CarouselProducts.APARTMENT);
        checkInsuranceProductsSteps.pressCarouselController(CarouselController.NEXT);
        checkInsuranceProductsSteps.assertCarouselControllerVisible(CarouselController.PREV, true);
        checkInsuranceProductsSteps.assertCarouselControllerVisible(CarouselController.NEXT, true);
        checkInsuranceProductsSteps.assertCarouselContainerText(CarouselProducts.DMSSTANDARD);
        checkInsuranceProductsSteps.pressCarouselController(CarouselController.NEXT);
        checkInsuranceProductsSteps.assertCarouselControllerVisible(CarouselController.PREV, true);
        checkInsuranceProductsSteps.assertCarouselControllerVisible(CarouselController.NEXT, false);
        checkInsuranceProductsSteps.assertCarouselContainerText(CarouselProducts.TRAVEL);
    }

    @DisplayName("Проверка категории 'Добровольное медицинское страхование'")
    @Description("Тест направлен на проверку наличия продуктов страхования" +
            " на странице категории 'Добровольное медицинское страхование'")
    @TmsLink("LIB5-3936")
    @Test
    public void checkDmsCategoryTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.DMS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        assertAll(
                () -> checkInsuranceProductsSteps.assertProductText(InsuranceProducts.STANDARD, InsuranceType.DMS),
                () -> checkInsuranceProductsSteps.assertProductText(InsuranceProducts.STANDARDPLUS, InsuranceType.DMS),
                () -> checkInsuranceProductsSteps.assertProductText(InsuranceProducts.PREMIUM, InsuranceType.DMS),
                () -> checkInsuranceProductsSteps.assertProductText(InsuranceProducts.VIP, InsuranceType.DMS)
        );
    }

    @DisplayName("Проверка элементов категории страхование'Добровольное медицинское страхование'")
    @Description("Тест направлен на проверку работы элементов" +
            " на странице категории 'Добровольное медицинское страхование'")
    @TmsLink("LIB5-3941")
    @Test
    public void checkDmsElementsTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.DMS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.STANDARD, InsuranceType.DMS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonDms();
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.STANDARDPLUS, InsuranceType.DMS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonDms();
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.PREMIUM, InsuranceType.DMS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonDms();
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.VIP, InsuranceType.DMS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonDms();
        checkInsuranceProductsSteps.pressBackButtonDms();
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getCurrWindowUrl(),
                checkInsuranceProductsSteps.getProductTypesWindowUrl(), true);
    }

    @DisplayName("Проверка категории 'Автострахование'")
    @Description("Тест направлен на проверку наличия продуктов страхования" +
            " на странице категории 'Автострахование'")
    @TmsLink("LIB5-3937")
    @Test
    public void checkCarsCategoryTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.CARS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        assertAll(
                () -> checkInsuranceProductsSteps.assertProductText(InsuranceProducts.KASKO, InsuranceType.CARS),
                () -> checkInsuranceProductsSteps.assertProductText(InsuranceProducts.OSAGO, InsuranceType.CARS)
        );
    }

    @DisplayName("Проверка элементов категории страхование'Автострахование'")
    @Description("Тест направлен на проверку работы элементов" +
            " на странице категории 'Автострахование'")
    @TmsLink("LIB5-3942")
    @Test
    public void checkCarsElementsTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.CARS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.KASKO, InsuranceType.CARS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonCars();
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.OSAGO, InsuranceType.CARS);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonCars();
        checkInsuranceProductsSteps.pressBackButtonCars();
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getCurrWindowUrl(),
                checkInsuranceProductsSteps.getProductTypesWindowUrl(), true);
    }

    @DisplayName("Проверка категории 'Страхование имущества'")
    @Description("Тест направлен на проверку наличия продуктов страхования" +
            " на странице категории 'Страхование имущества'")
    @TmsLink("LIB5-3938")
    @Test
    public void checkPropertyCategoryTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.PROPERTY);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        assertAll(
                () -> checkInsuranceProductsSteps.assertProductText(InsuranceProducts.APARTMENT, InsuranceType.PROPERTY),
                () -> checkInsuranceProductsSteps.assertProductText(InsuranceProducts.HOUSE, InsuranceType.PROPERTY),
                () -> checkInsuranceProductsSteps.assertProductText(InsuranceProducts.CONTENTS, InsuranceType.PROPERTY)
        );
    }

    @DisplayName("Проверка элементов категории страхование'Страхование имущества'")
    @Description("Тест направлен на проверку работы элементов" +
            " на странице категории 'Страхование имущества'")
    @TmsLink("LIB5-3943")
    @Test
    public void checkPropertyElementsTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.PROPERTY);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.HOUSE, InsuranceType.PROPERTY);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonProperty();
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.APARTMENT, InsuranceType.PROPERTY);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonProperty();
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.CONTENTS, InsuranceType.PROPERTY);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonProperty();
        checkInsuranceProductsSteps.pressBackButtonProperty();
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getCurrWindowUrl(),
                checkInsuranceProductsSteps.getProductTypesWindowUrl(), true);
    }

    @DisplayName("Проверка категории 'Страхование выезжающих за границу'")
    @Description("Тест направлен на проверку наличия продуктов страхования" +
            " на странице категории 'Страхование выезжающих за границу'")
    @TmsLink("LIB5-3939")
    @Test
    public void checkTravelCategoryTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.TRAVEL);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        checkInsuranceProductsSteps.assertProductText(InsuranceProducts.TRAVEL, InsuranceType.TRAVEL);
    }

    @DisplayName("Проверка элементов категории страхование'Страхование выезжающих за границу'")
    @Description("Тест направлен на проверку работы элементов" +
            " на странице категории 'Страхование выезжающих за границу'")
    @TmsLink("LIB5-3945")
    @Test
    public void checkTravelElementsTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.TRAVEL);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.TRAVEL, InsuranceType.TRAVEL);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonTravel();
        checkInsuranceProductsSteps.pressBackButtonTravel();
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getCurrWindowUrl(),
                checkInsuranceProductsSteps.getProductTypesWindowUrl(), true);
    }

    @DisplayName("Проверка категории 'Страхование от несчастных случаев'")
    @Description("Тест направлен на проверку наличия продуктов страхования" +
            " на странице категории 'Страхование от несчастных случаев'")
    @TmsLink("LIB5-3940")
    @Test
    public void checkAccidentCategoryTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.ACCIDENT);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        checkInsuranceProductsSteps.assertProductText(InsuranceProducts.ACCIDENT, InsuranceType.ACCIDENT);
    }

    @DisplayName("Проверка элементов категории страхование'Страхование от несчастных случаев'")
    @Description("Тест направлен на проверку работы элементов" +
            " на странице категории 'Страхование от несчастных случаев'")
    @TmsLink("LIB5-3944")
    @Test
    public void checkAccidentElementsTest() {
        checkInsuranceProductsSteps.selectInsuranceCategory(InsuranceType.ACCIDENT);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductTypesWindowUrl(),
                checkInsuranceProductsSteps.getProductCategoryWindowUrl(), false);
        checkInsuranceProductsSteps.selectInsuranceProduct(InsuranceProducts.ACCIDENT, InsuranceType.ACCIDENT);
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getProductCategoryWindowUrl(),
                checkInsuranceProductsSteps.getCurrentProductWindowUrl(), false);
        checkInsuranceProductsSteps.pressBackButtonAccident();
        checkInsuranceProductsSteps.pressBackButtonAccident();
        checkInsuranceProductsSteps.assertWindowUrl(checkInsuranceProductsSteps.getCurrWindowUrl(),
                checkInsuranceProductsSteps.getProductTypesWindowUrl(), true);
    }

}
