package web.steps.insuranceSteps;

import io.qameta.allure.Step;
import web.constans.insurance.InsuranceEnum.CarouselController;
import web.constans.insurance.InsuranceEnum.CarouselProducts;
import web.constans.insurance.InsuranceEnum.InsuranceProducts;
import web.constans.insurance.InsuranceEnum.InsuranceType;
import web.helpers.TestListener;
import web.pages.insurancePages.InsuranceProductsPage;
import web.pages.insurancePages.accident.AccidentInsuranceTypesPage;
import web.pages.insurancePages.auto.CarsInsuranceTypesPage;
import web.pages.insurancePages.dms.DmsTypesPage;
import web.pages.insurancePages.property.PropertyInsuranceTypesPage;
import web.pages.insurancePages.travel.TravelInsuranceTypesPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static web.constans.insurance.InsuranceServiceConstants.PPRODUCT_PROPERTY_CONTENTS;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_ACCIDENT;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_CARS_KASKO;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_CARS_OSAGO;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_DMS_PREMIUM;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_DMS_STANDART;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_DMS_STANDART_PLUS;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_DMS_VIP;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_PROPERTY_APARTMENT;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_PROPERTY_HOUSE;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_TRAVEL;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_TYPE_ACCIDENT;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_TYPE_CARS;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_TYPE_DMS;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_TYPE_PROPERTY;
import static web.constans.insurance.InsuranceServiceConstants.PRODUCT_TYPE_TRAVEL;
import static web.helpers.Waiters.waitPageLoad;

public class CheckInsuranceProductsSteps {
    protected InsuranceProductsPage insuranceProductsPage;
    protected AccidentInsuranceTypesPage accidentInsuranceTypesPage;
    protected CarsInsuranceTypesPage carsInsuranceTypesPage;
    protected DmsTypesPage dmsTypesPage;
    protected TravelInsuranceTypesPage travelInsuranceTypesPage;
    protected PropertyInsuranceTypesPage propertyInsuranceTypesPage;
    private String productTypesWindowUrl;
    private String productCategoryWindowUrl;
    private String currentProductWindowUrl;
    private String currWindowUrl;

    public CheckInsuranceProductsSteps() {
        insuranceProductsPage = new InsuranceProductsPage();
        accidentInsuranceTypesPage = new AccidentInsuranceTypesPage();
        carsInsuranceTypesPage = new CarsInsuranceTypesPage();
        dmsTypesPage = new DmsTypesPage();
        travelInsuranceTypesPage = new TravelInsuranceTypesPage();
        propertyInsuranceTypesPage = new PropertyInsuranceTypesPage();
    }

    public void setProductTypesWindowUrl() {
        productTypesWindowUrl = insuranceProductsPage.getWindowUrl();
    }

    public String getProductTypesWindowUrl() {
        return productTypesWindowUrl;
    }

    public String getProductCategoryWindowUrl() {
        return productCategoryWindowUrl;
    }

    public String getCurrentProductWindowUrl() {
        return currentProductWindowUrl;
    }

    public String getCurrWindowUrl() {
        return currWindowUrl;
    }

    @Step("Выбрать категорию страхования {insuranceType}")
    public void selectInsuranceCategory(InsuranceType insuranceType) {
        switch (insuranceType) {
            case DMS:
                insuranceProductsPage.selectDmsCategory();
                waitPageLoad();
                productCategoryWindowUrl = dmsTypesPage.getWindowUrl();
                TestListener.takeScreenshot();
                break;
            case TRAVEL:
                insuranceProductsPage.selectTravelCategory();
                waitPageLoad();
                productCategoryWindowUrl = travelInsuranceTypesPage.getWindowUrl();
                TestListener.takeScreenshot();
                break;
            case CARS:
                insuranceProductsPage.selectCarsCategory();
                waitPageLoad();
                productCategoryWindowUrl = carsInsuranceTypesPage.getWindowUrl();
                TestListener.takeScreenshot();
                break;
            case ACCIDENT:
                insuranceProductsPage.selectAccidentCategory();
                waitPageLoad();
                productCategoryWindowUrl = accidentInsuranceTypesPage.getWindowUrl();
                TestListener.takeScreenshot();
                break;
            case PROPERTY:
                insuranceProductsPage.selectPropertyCategory();
                waitPageLoad();
                productCategoryWindowUrl = propertyInsuranceTypesPage.getWindowUrl();
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
                assertEquals(insuranceProductsPage.getDmsCategoryText(), PRODUCT_TYPE_DMS,
                        "Текст категории не совпадает с эталоном");
                break;
            case CARS:
                assertEquals(insuranceProductsPage.getCarCategoryText(), PRODUCT_TYPE_CARS,
                        "Текст категории не совпадает с эталоном");
                break;
            case TRAVEL:
                assertEquals(insuranceProductsPage.getTravelCategoryText(),
                        PRODUCT_TYPE_TRAVEL,
                        "Текст категории не совпадает с эталоном");
                break;
            case ACCIDENT:
                assertEquals(insuranceProductsPage.getAccidentCategoryText(),
                        PRODUCT_TYPE_ACCIDENT,
                        "Текст категории не совпадает с эталоном");
                break;
            case PROPERTY:
                assertEquals(insuranceProductsPage.getPropertyCategoryText(), PRODUCT_TYPE_PROPERTY,
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
                        PRODUCT_DMS_STANDART,
                        "Текст не соответствует эталону");
                break;
            case KASKO:
                assertEquals(insuranceProductsPage.getKaskoContainerText(),
                        PRODUCT_CARS_KASKO,
                        "Текст не соответствует эталону");
                break;
            case TRAVEL:
                assertEquals(insuranceProductsPage.getTravelContainerText(),
                        PRODUCT_TRAVEL,
                        "Текст не соответствует эталону");
                break;
            case APARTMENT:
                assertEquals(insuranceProductsPage.getApartmentContainerText(),
                        PRODUCT_PROPERTY_APARTMENT,
                        "Текст не соответствует эталону");
                break;
            case ACCIDENT:
                assertEquals(insuranceProductsPage.getAccidentContainerText(),
                        PRODUCT_ACCIDENT,
                        "Текст не соответствует эталону");
                break;
        }
    }

    @Step("Нажать кнопку 'Подробнее' элемента карусели {carouselProduct}")
    public void pressCarouselProductButton(CarouselProducts carouselProduct) {
        switch (carouselProduct) {
            case KASKO:
                insuranceProductsPage.pressKaskoContainerButton();
                waitPageLoad();
                currentProductWindowUrl = carsInsuranceTypesPage.getWindowUrl();
                TestListener.takeScreenshot();
                break;
            case ACCIDENT:
                insuranceProductsPage.pressAccidentContainerButton();
                waitPageLoad();
                currentProductWindowUrl = accidentInsuranceTypesPage.getWindowUrl();
                TestListener.takeScreenshot();
                break;
            case APARTMENT:
                insuranceProductsPage.pressApartmentContainerButton();
                waitPageLoad();
                currentProductWindowUrl = propertyInsuranceTypesPage.getWindowUrl();
                TestListener.takeScreenshot();
                break;
            case TRAVEL:
                insuranceProductsPage.pressTravelContainerButton();
                waitPageLoad();
                currentProductWindowUrl = travelInsuranceTypesPage.getWindowUrl();
                TestListener.takeScreenshot();

                break;
            case DMSSTANDARD:
                insuranceProductsPage.pressDmsContainerButton();
                waitPageLoad();
                currentProductWindowUrl = dmsTypesPage.getWindowUrl();
                TestListener.takeScreenshot();
                break;
        }
    }


    @Step("Проверка текста продукта {product} в категории {category}")
    public void assertProductText(InsuranceProducts product, InsuranceType category) {
        switch (category) {
            case DMS:
                switch (product) {
                    case STANDARD:
                        assertEquals(dmsTypesPage.getWidgetDmsStandartText(),
                                PRODUCT_DMS_STANDART,
                                "Текст не соответствует эталону");
                        break;
                    case STANDARDPLUS:
                        assertEquals(dmsTypesPage.getWidgetDmsStandartPlusText(),
                                PRODUCT_DMS_STANDART_PLUS,
                                "Текст не соответствует эталону");
                        break;
                    case PREMIUM:
                        assertEquals(dmsTypesPage.getWidgetDmsPremiumText(),
                                PRODUCT_DMS_PREMIUM,
                                "Текст не соответствует эталону");
                        break;
                    case VIP:
                        assertEquals(dmsTypesPage.getWidgetDmsVipText(),
                                PRODUCT_DMS_VIP,
                                "Текст не соответствует эталону");
                        break;
                }
            case CARS:
                switch (product) {
                    case KASKO:
                        assertEquals(carsInsuranceTypesPage.getWidgetKaskoText(),
                                PRODUCT_CARS_KASKO,
                                "Текст не соответствует эталону");
                        break;
                    case OSAGO:
                        assertEquals(carsInsuranceTypesPage.getWidgetOsagoText(),
                                PRODUCT_CARS_OSAGO,
                                "Текст не соответствует эталону");
                        break;
                }
            case PROPERTY:
                switch (product) {
                    case APARTMENT:
                        assertEquals(propertyInsuranceTypesPage.getWidgetAppartmentText(),
                                PRODUCT_PROPERTY_APARTMENT,
                                "Текст не соответствует эталону");
                        break;
                    case HOUSE:
                        assertEquals(propertyInsuranceTypesPage.getWidgetHouseText(),
                                PRODUCT_PROPERTY_HOUSE,
                                "Текст не соответствует эталону");
                        break;
                    case CONTENTS:
                        assertEquals(propertyInsuranceTypesPage.getWidgetContentsText(),
                                PPRODUCT_PROPERTY_CONTENTS,
                                "Текст не соответствует эталону");
                        break;
                }
            case TRAVEL:
                if (product == InsuranceProducts.TRAVEL) {
                    assertEquals(travelInsuranceTypesPage.getWidgetTravelText(),
                            PRODUCT_TRAVEL,
                            "Текст не соответствует эталону");
                }
            case ACCIDENT:
                if (product == InsuranceProducts.ACCIDENT) {
                    assertEquals(accidentInsuranceTypesPage.getWidgetAccidentText(),
                            PRODUCT_ACCIDENT,
                            "Текст не соответствует эталону");
                }
        }
    }

    @Step("Нажать на кнопку 'Подробнее' продукта {product} в категории {category}")
    public void selectInsuranceProduct(InsuranceProducts product, InsuranceType category) {
        switch (category) {
            case DMS:
                switch (product) {
                    case STANDARD:
                        dmsTypesPage.pressWidgetDmsStandartButton();
                        waitPageLoad();
                        currentProductWindowUrl = dmsTypesPage.getWindowUrl();
                        TestListener.takeScreenshot();
                        break;
                    case STANDARDPLUS:
                        dmsTypesPage.pressWidgetDmsStandartPlusButton();
                        waitPageLoad();
                        currentProductWindowUrl = dmsTypesPage.getWindowUrl();
                        TestListener.takeScreenshot();
                        break;
                    case PREMIUM:
                        dmsTypesPage.pressWidgetDmsPremiumButton();
                        waitPageLoad();
                        currentProductWindowUrl = dmsTypesPage.getWindowUrl();
                        TestListener.takeScreenshot();
                        break;
                    case VIP:
                        dmsTypesPage.pressWidgetDmsVipButton();
                        waitPageLoad();
                        currentProductWindowUrl = dmsTypesPage.getWindowUrl();
                        TestListener.takeScreenshot();
                        break;
                }
            case CARS:
                switch (product) {
                    case KASKO:
                        carsInsuranceTypesPage.pressWidgetKaskoButton();
                        waitPageLoad();
                        currentProductWindowUrl = carsInsuranceTypesPage.getWindowUrl();
                        TestListener.takeScreenshot();
                        break;
                    case OSAGO:
                        carsInsuranceTypesPage.pressWidgetOsagoButton();
                        waitPageLoad();
                        currentProductWindowUrl = carsInsuranceTypesPage.getWindowUrl();
                        TestListener.takeScreenshot();
                        break;
                }
            case PROPERTY:
                switch (product) {
                    case APARTMENT:
                        propertyInsuranceTypesPage.pressWidgetApartmentButton();
                        waitPageLoad();
                        currentProductWindowUrl = propertyInsuranceTypesPage.getWindowUrl();
                        TestListener.takeScreenshot();
                        break;
                    case HOUSE:
                        propertyInsuranceTypesPage.pressWidgetHouseButton();
                        waitPageLoad();
                        currentProductWindowUrl = propertyInsuranceTypesPage.getWindowUrl();
                        TestListener.takeScreenshot();
                        break;
                    case CONTENTS:
                        propertyInsuranceTypesPage.pressWidgetContentsButton();
                        waitPageLoad();
                        currentProductWindowUrl = propertyInsuranceTypesPage.getWindowUrl();
                        TestListener.takeScreenshot();
                        break;
                }
            case TRAVEL:
                if (product == InsuranceProducts.TRAVEL) {
                    travelInsuranceTypesPage.pressWidgetTravelButton();
                    waitPageLoad();
                    currentProductWindowUrl = travelInsuranceTypesPage.getWindowUrl();
                    TestListener.takeScreenshot();
                }
            case ACCIDENT:
                if (product == InsuranceProducts.ACCIDENT) {
                    accidentInsuranceTypesPage.pressWidgetAccidentButton();
                    waitPageLoad();
                    currentProductWindowUrl = accidentInsuranceTypesPage.getWindowUrl();
                    TestListener.takeScreenshot();
                }
        }
    }

    @Step("Проверить URL страницы")
    public void assertWindowUrl(String firstUrl, String secondUrl, boolean status) {
        assertEquals(firstUrl.equals(secondUrl), status, "URL match error");
    }

    @Step("Нажать кнопку 'Назад' на странице категории ДМС")
    public void pressBackButtonDms() {
        dmsTypesPage.pressBackButton();
        waitPageLoad();
        currWindowUrl = insuranceProductsPage.getWindowUrl();
    }

    @Step("Нажать кнопку 'Назад' на странице категории Автострахование")
    public void pressBackButtonCars() {
        carsInsuranceTypesPage.pressBackButton();
        waitPageLoad();
        currWindowUrl = insuranceProductsPage.getWindowUrl();
    }

    @Step("Нажать кнопку 'Назад' на странице категории Имущество")
    public void pressBackButtonProperty() {
        propertyInsuranceTypesPage.pressBackButton();
        waitPageLoad();
        currWindowUrl = insuranceProductsPage.getWindowUrl();
    }

    @Step("Нажать кнопку 'Назад' на странице категории Несчастные случаи")
    public void pressBackButtonAccident() {
        accidentInsuranceTypesPage.pressBackButton();
        waitPageLoad();
        currWindowUrl = insuranceProductsPage.getWindowUrl();
    }

    @Step("Нажать кнопку 'Назад' на странице категории Выезжающих за границу")
    public void pressBackButtonTravel() {
        travelInsuranceTypesPage.pressBackButton();
        waitPageLoad();
        currWindowUrl = insuranceProductsPage.getWindowUrl();
    }
}
