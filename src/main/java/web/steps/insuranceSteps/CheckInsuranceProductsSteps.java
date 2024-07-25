package web.steps.insuranceSteps;

import io.qameta.allure.Step;
import web.helpers.TestListener;
import web.pages.insurancePages.InsuranceProductsPage;
import web.pages.insurancePages.accident.AccidentInsuranceTypesPage;
import web.pages.insurancePages.auto.CarsInsuranceTypesPage;
import web.pages.insurancePages.dms.DmsTypesPage;
import web.pages.insurancePages.property.PropertyInsuranceTypesPage;
import web.pages.insurancePages.travel.TravelInsuranceTypesPage;
import web.constans.insurance.InsuranceEnum.InsuranceType;
import web.constans.insurance.InsuranceEnum.CarouselController;
import web.constans.insurance.InsuranceEnum.CarouselProducts;
import web.constans.insurance.InsuranceEnum.InsuranceProducts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckInsuranceProductsSteps {
    protected InsuranceProductsPage insuranceProductsPage;
    protected AccidentInsuranceTypesPage accidentInsuranceTypesPage;
    protected CarsInsuranceTypesPage carsInsuranceTypesPage;
    protected DmsTypesPage dmsTypesPage;
    protected TravelInsuranceTypesPage travelInsuranceTypesPage;
    protected PropertyInsuranceTypesPage propertyInsuranceTypesPage;
    private String productTypesWindowHandle;
    private String productCategoryWindowHandle;
    private String currentProductWindowHandle;
    private String currWindowHandle;

    public CheckInsuranceProductsSteps() {
        insuranceProductsPage = new InsuranceProductsPage();
        accidentInsuranceTypesPage = new AccidentInsuranceTypesPage();
        carsInsuranceTypesPage = new CarsInsuranceTypesPage();
        dmsTypesPage = new DmsTypesPage();
        travelInsuranceTypesPage = new TravelInsuranceTypesPage();
        propertyInsuranceTypesPage = new PropertyInsuranceTypesPage();
    }

    public void setProductTypesWindowHandle() {
        productTypesWindowHandle = insuranceProductsPage.getWindowHandle();
    }

    public String getProductTypesWindowHandle() {
        return productTypesWindowHandle;
    }

    public String getProductCategoryWindowHandle() {
        return productCategoryWindowHandle;
    }

    public String getCurrentProductWindowHandle() {
        return currentProductWindowHandle;
    }

    @Step("Выбрать категорию страхования {insuranceType}")
    public void selectInsuranceCategory(InsuranceType insuranceType) {
        switch (insuranceType) {
            case DMS:
                insuranceProductsPage.selectDmsCategory();
                productCategoryWindowHandle = dmsTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
            case TRAVEL:
                insuranceProductsPage.selectTravelCategory();
                productCategoryWindowHandle = travelInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
            case CARS:
                insuranceProductsPage.selectCarsCategory();
                productCategoryWindowHandle = carsInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
            case ACCIDENT:
                insuranceProductsPage.selectAccidentCategory();
                productCategoryWindowHandle = accidentInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
            case PROPERTY:
                insuranceProductsPage.selectPropertyCategory();
                productCategoryWindowHandle = propertyInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
        }
    }

    @Step("Проверить наличие категории {insuranceType}")
    public void assertInsuranceCategoryPresent(InsuranceType insuranceType) {
        switch (insuranceType) {
            case DMS:
                assertTrue(insuranceProductsPage.dmsIconPresent(), "Отсутствует категория ДМС");
                break;
            case TRAVEL:
                assertTrue(insuranceProductsPage.travelIconPresent(), "Отсутствует категория " +
                        "'выезжающие за границу'");
                break;
            case CARS:
                assertTrue(insuranceProductsPage.carIconPresent(), "Отсутствует категория Автострахование");
                break;
            case ACCIDENT:
                assertTrue(insuranceProductsPage.accidentIconPresent(), "Отсутствует категория " +
                        "'несчастные случаи'");
                break;
            case PROPERTY:
                assertTrue(insuranceProductsPage.propertyIconPresent(), "Отсутствует категория 'имущество'");
                break;
        }
    }

    @Step("Проверить текст категории {insuranceType}")
    public void assertInsuranceCategoryText(InsuranceType insuranceType) {
        switch (insuranceType) {
            case DMS:
                assertEquals(insuranceProductsPage.getDmsCategoryText(), "Медицинское страхование",
                        "Текст категории не совпадает с эталоном");
                break;
            case CARS:
                assertEquals(insuranceProductsPage.getCarCategoryText(), "Автострахование",
                        "Текст категории не совпадает с эталоном");
                break;
            case TRAVEL:
                assertEquals(insuranceProductsPage.getTravelCategoryText(),
                        "Страхование выезжающих за границу",
                        "Текст категории не совпадает с эталоном");
                break;
            case ACCIDENT:
                assertEquals(insuranceProductsPage.getAccidentCategoryText(),
                        "Страхование от несчастных случаев",
                        "Текст категории не совпадает с эталоном");
                break;
            case PROPERTY:
                assertEquals(insuranceProductsPage.getPropertyCategoryText(), "Страхование имущества",
                        "Текст категории не совпадает с эталоном");
                break;
        }
    }

    @Step("Проверить отображение контроллера карусели {carouselController}")
    public void assertCarouselControllerVisible(CarouselController carouselController, boolean status) {
        String message = status ? "Кнопка не отображается" : "Кнопка отображается";
        switch (carouselController) {
            case NEXT:
                assertEquals(insuranceProductsPage.carouselNextButtonPresent(), status, message);
                break;
            case PREV:
                assertEquals(insuranceProductsPage.carouselPrevButtonPresent(), status, message);
                break;
        }
    }

    @Step("Нажать на контроллер карусели {carouselController}")
    public void pressCarouselController(CarouselController carouselController) {
        switch (carouselController) {
            case NEXT:
                insuranceProductsPage.pressCarouselNextButton();
                break;
            case PREV:
                insuranceProductsPage.pressCarouselPrevButton();
                break;
        }
    }

    @Step("Проверить текст контейнера карусели {carouselProduct}")
    public void assertCarouselContainerText(CarouselProducts carouselProduct) {
        switch (carouselProduct) {
            case DMSSTANDARD:
                assertEquals(insuranceProductsPage.getDmsContainerText(),
                        "Добровольное медицинское срахование Standard",
                        "Текст не соответствует эталону");
                break;
            case KASKO:
                assertEquals(insuranceProductsPage.getKaskoContainerText(),
                        "Автострахование КАСКО",
                        "Текст не соответствует эталону");
                break;
            case TRAVEL:
                assertEquals(insuranceProductsPage.getTravelContainerText(),
                        "Страхование выезжающих за границу",
                        "Текст не соответствует эталону");
                break;
            case APARTMENT:
                assertEquals(insuranceProductsPage.getApartmentContainerText(),
                        "Страхование квартиры",
                        "Текст не соответствует эталону");
                break;
            case ACCIDENT:
                assertEquals(insuranceProductsPage.getAccidentContainerText(),
                        "Страхование от несчастных случаев",
                        "Текст не соответствует эталону");
                break;
        }
    }

    @Step("Нажать кнопку 'Подробнее' элемента карусели {carouselProduct}")
    public void pressCarouselProductButton(CarouselProducts carouselProduct) {
        switch (carouselProduct) {
            case KASKO:
                insuranceProductsPage.pressKaskoContainerButton();
                currentProductWindowHandle = carsInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
            case ACCIDENT:
                insuranceProductsPage.pressAccidentContainerButton();
                currentProductWindowHandle = accidentInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
            case APARTMENT:
                insuranceProductsPage.pressApartmentContainerButton();
                currentProductWindowHandle = propertyInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
            case TRAVEL:
                insuranceProductsPage.pressTravelContainerButton();
                currentProductWindowHandle = travelInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
            case DMSSTANDARD:
                insuranceProductsPage.pressDmsContainerButton();
                currentProductWindowHandle = dmsTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
                break;
        }
    }

    @Step("Проверка того что окно сменилось")
    public void assertWidndowsAreDifferent(String window1, String window2) {
        assertNotEquals(window1, window2, "То же самое окно");
    }

    @Step("Проверка кнопки Назад")
    public void assertBackButtonWorking() {
        assertEquals(productTypesWindowHandle, currWindowHandle, "Страница не переключилась назад");
    }

    @Step("Проверка текста продукта {product} в категории {category}")
    public void assertProductText(InsuranceProducts product, InsuranceType category) {
        switch (category) {
            case DMS:
                switch (product) {
                    case STANDARD:
                        assertEquals(dmsTypesPage.getWidgetDmsStandartText(),
                                "Добровольное медицинское страхование Standard",
                                "Текст не соответствует эталону");
                        break;
                    case STANDARDPLUS:
                        assertEquals(dmsTypesPage.getWidgetDmsStandartPlusText(),
                                "Добровольное медицинское страхование Standard+",
                                "Текст не соответствует эталону");
                        break;
                    case PREMIUM:
                        assertEquals(dmsTypesPage.getWidgetDmsPremiumText(),
                                "Добровольное медицинское страхование Premium",
                                "Текст не соответствует эталону");
                        break;
                    case VIP:
                        assertEquals(dmsTypesPage.getWidgetDmsVipText(),
                                "Добровольное медицинское страхование VIP",
                                "Текст не соответствует эталону");
                        break;
                }
            case CARS:
                switch (product) {
                    case KASKO:
                        assertEquals(carsInsuranceTypesPage.getWidgetKaskoText(),
                                "Автострахование КАСКО",
                                "Текст не соответствует эталону");
                        break;
                    case OSAGO:
                        assertEquals(carsInsuranceTypesPage.getWidgetOsagoText(),
                                "Автострахование ОСАГО",
                                "Текст не соответствует эталону");
                        break;
                }
            case PROPERTY:
                switch (product) {
                    case APARTMENT:
                        assertEquals(propertyInsuranceTypesPage.getWidgetAppartmentText(),
                                "Страхование квартиры",
                                "Текст не соответствует эталону");
                        break;
                    case HOUSE:
                        assertEquals(propertyInsuranceTypesPage.getWidgetHouseText(),
                                "Страхование дома",
                                "Текст не соответствует эталону");
                        break;
                    case CONTENTS:
                        assertEquals(propertyInsuranceTypesPage.getWidgetContentsText(),
                                "Страхование домашнего имущества",
                                "Текст не соответствует эталону");
                        break;
                }
            case TRAVEL:
                if (product == InsuranceProducts.TRAVEL) {
                    assertEquals(travelInsuranceTypesPage.getWidgetTravelText(),
                            "Страхование выезжающих за границу",
                            "Текст не соответствует эталону");
                }
            case ACCIDENT:
                if (product == InsuranceProducts.ACCIDENT) {
                    assertEquals(accidentInsuranceTypesPage.getWidgetAccidentText(),
                            "Страхование от несчастных случаев",
                            "Текст не соответствует эталону");
                }
        }
    }

    @Step("Нажать на кнопку 'Подробнее' продукта {product} в категории {category}")
    public void selectDmsProduct(InsuranceProducts product, InsuranceType category) {
        switch (category) {
            case DMS:
                switch (product) {
                    case STANDARD:
                        dmsTypesPage.pressWidgetDmsStandartButton();
                        currentProductWindowHandle = dmsTypesPage.getWindowHandle();
                        TestListener.takeScreenshot();
                        break;
                    case STANDARDPLUS:
                        dmsTypesPage.pressWidgetDmsStandartPlusButton();
                        currentProductWindowHandle = dmsTypesPage.getWindowHandle();
                        TestListener.takeScreenshot();
                        break;
                    case PREMIUM:
                        dmsTypesPage.pressWidgetDmsPremiumButton();
                        currentProductWindowHandle = dmsTypesPage.getWindowHandle();
                        TestListener.takeScreenshot();
                        break;
                    case VIP:
                        dmsTypesPage.pressWidgetDmsVipButton();
                        currentProductWindowHandle = dmsTypesPage.getWindowHandle();
                        TestListener.takeScreenshot();
                        break;
                }
            case CARS:
                switch (product) {
                    case KASKO:
                        carsInsuranceTypesPage.pressWidgetKaskoButton();
                        currentProductWindowHandle = carsInsuranceTypesPage.getWindowHandle();
                        TestListener.takeScreenshot();
                        break;
                    case OSAGO:
                        carsInsuranceTypesPage.pressWidgetOsagoButton();
                        currentProductWindowHandle = carsInsuranceTypesPage.getWindowHandle();
                        TestListener.takeScreenshot();
                        break;
                }
            case PROPERTY:
                switch (product) {
                    case APARTMENT:
                        propertyInsuranceTypesPage.pressWidgetApartmentButton();
                        currentProductWindowHandle = propertyInsuranceTypesPage.getWindowHandle();
                        TestListener.takeScreenshot();
                        break;
                    case HOUSE:
                        propertyInsuranceTypesPage.pressWidgetHouseButton();
                        currentProductWindowHandle = propertyInsuranceTypesPage.getWindowHandle();
                        TestListener.takeScreenshot();
                        break;
                    case CONTENTS:
                        propertyInsuranceTypesPage.pressWidgetContentsButton();
                        currentProductWindowHandle = propertyInsuranceTypesPage.getWindowHandle();
                        TestListener.takeScreenshot();
                        break;
                }
            case TRAVEL:
                if (product == InsuranceProducts.TRAVEL) {
                    travelInsuranceTypesPage.pressWidgetTravelButton();
                    currentProductWindowHandle = travelInsuranceTypesPage.getWindowHandle();
                    TestListener.takeScreenshot();
                }
            case ACCIDENT:
                if (product == InsuranceProducts.ACCIDENT) {
                    accidentInsuranceTypesPage.pressWidgetAccidentButton();
                    currentProductWindowHandle = accidentInsuranceTypesPage.getWindowHandle();
                    TestListener.takeScreenshot();
                }
        }
    }

    @Step("Нажать кнопку 'Назад' на странице категории ДМС")
    public void pressBackButtonDms() {
        dmsTypesPage.pressBackButton();
        currWindowHandle = insuranceProductsPage.getWindowHandle();
    }

    @Step("Нажать кнопку 'Назад' на странице категории Автострахование")
    public void pressBackButtonCars() {
        carsInsuranceTypesPage.pressBackButton();
        currWindowHandle = insuranceProductsPage.getWindowHandle();
    }

    @Step("Нажать кнопку 'Назад' на странице категории Имущество")
    public void pressBackButtonProperty() {
        propertyInsuranceTypesPage.pressBackButton();
        currWindowHandle = insuranceProductsPage.getWindowHandle();
    }

    @Step("Нажать кнопку 'Назад' на странице категории Несчастные случаи")
    public void pressBackButtonAccident() {
        accidentInsuranceTypesPage.pressBackButton();
        currWindowHandle = insuranceProductsPage.getWindowHandle();
    }

    @Step("Нажать кнопку 'Назад' на странице категории Выезжающих за границу")
    public void pressBackButtonTravel() {
        travelInsuranceTypesPage.pressBackButton();
        currWindowHandle = insuranceProductsPage.getWindowHandle();
    }
}
