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

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Step("Выбрать категорию страхования {insuranceType}")
    public void selectInsuranceCategory(InsuranceType insuranceType){
        switch (insuranceType){
            case DMS: insuranceProductsPage.selectDmsCategory();
            productCategoryWindowHandle = dmsTypesPage.getWindowHandle();
            break;
            case TRAVEL: insuranceProductsPage.selectTravelCategory();
            productCategoryWindowHandle = travelInsuranceTypesPage.getWindowHandle();
            break;
            case CARS: insuranceProductsPage.selectCarsCategory();
            productCategoryWindowHandle = carsInsuranceTypesPage.getWindowHandle();
            break;
            case ACCIDENT: insuranceProductsPage.selectAccidentCategory();
            productCategoryWindowHandle = accidentInsuranceTypesPage.getWindowHandle();
            break;
            case PROPERTY: insuranceProductsPage.selectPropertyCategory();
            productCategoryWindowHandle = propertyInsuranceTypesPage.getWindowHandle();
            break;
        }
    }
    @Step("Проверить наличие категории {insuranceType}")
    public void assertInsuranceCategoryPresent (InsuranceType insuranceType){
        switch (insuranceType){
            case DMS: assertTrue(insuranceProductsPage.dmsIconPresent(), "Отсутствует категория ДМС");
            break;
            case TRAVEL: assertTrue(insuranceProductsPage.travelIconPresent(), "Отсутствует категория " +
                    "'выезжающие за границу'");
            break;
            case CARS: assertTrue(insuranceProductsPage.carIconPresent(), "Отсутствует категория Автострахование");
            break;
            case ACCIDENT: assertTrue(insuranceProductsPage.accidentIconPresent(), "Отсутствует категория " +
                    "'несчастные случаи'");
            break;
            case PROPERTY: assertTrue(insuranceProductsPage.propertyIconPresent(), "Отсутствует категория 'имущество'");
            break;
        }
    }
    @Step("Проверить текст категории {insuranceType}")
    public void assertInsuranceCategoryText (InsuranceType insuranceType){
        switch (insuranceType){
            case DMS: assertEquals(insuranceProductsPage.getDmsCategoryText(), "Медицинское страхование",
                    "Текст категории не совпадает с эталоном");
            break;
            case CARS: assertEquals(insuranceProductsPage.getCarCategoryText(), "Автострахование",
                    "Текст категории не совпадает с эталоном");
                break;
            case TRAVEL: assertEquals(insuranceProductsPage.getTravelCategoryText(),
                    "Страхование выезжающих за границу",
                    "Текст категории не совпадает с эталоном");
                break;
            case ACCIDENT: assertEquals(insuranceProductsPage.getAccidentCategoryText(),
                    "Страхование от несчастных случаев",
                    "Текст категории не совпадает с эталоном");
                break;
            case PROPERTY: assertEquals(insuranceProductsPage.getPropertyCategoryText(), "Страхование имущества",
                    "Текст категории не совпадает с эталоном");
                break;
        }
    }

    @Step("Проверить отображение контроллера карусели {carouselController}")
    public void assertCarouselControllerVisible(CarouselController carouselController, boolean status){
        String message = status ? "Кнопка не отображается" : "Кнопка отображается";
        switch (carouselController) {
            case NEXT : assertEquals(insuranceProductsPage.carouselNextButtonPresent(), status, message);
            break;
            case PREV: assertEquals(insuranceProductsPage.carouselPrevButtonPresent(), status, message);
            break;
        }
    }

    @Step("Нажать на контроллер карусели {carouselController}")
    public void pressCarouselController(CarouselController carouselController){
        switch (carouselController){
            case NEXT: insuranceProductsPage.pressCarouselNextButton();
            break;
            case PREV: insuranceProductsPage.pressCarouselPrevButton();
            break;
        }
    }

    @Step("Проверить текст контейнера карусели {carouselProduct}")
    public void assertCarouselContainerText(CarouselProducts carouselProduct){
        switch (carouselProduct){
            case DMSSTANDARD: assertEquals(insuranceProductsPage.getDmsContainerText(),
                    "Добровольное медицинское срахование Standard",
                    "Текст не соответствует эталону");
            break;
            case KASKO: assertEquals(insuranceProductsPage.getKaskoContainerText(),
                    "Автострахование КАСКО",
                    "Текст не соответствует эталону");
            break;
            case TRAVEL: assertEquals(insuranceProductsPage.getTravelContainerText(),
                    "Страхование выезжающих за границу",
                    "Текст не соответствует эталону");
            break;
            case APARTMENT: assertEquals(insuranceProductsPage.getApartmentContainerText(),
                    "Страхование квартиры",
                    "Текст не соответствует эталону");
            break;
            case ACCIDENT: assertEquals(insuranceProductsPage.getAccidentContainerText(),
                    "Страхование от несчастных случаев",
                    "Текст не соответствует эталону");
            break;
        }
    }

    @Step("Нажать кнопку 'Подробнее' элемента карусели {carouselProduct}")
    public void pressCarouselProductButton(CarouselProducts carouselProduct){
        switch (carouselProduct){
            case KASKO: insuranceProductsPage.pressKaskoContainerButton();
                currentProductWindowHandle = carsInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
            break;
            case ACCIDENT: insuranceProductsPage.pressAccidentContainerButton();
                currentProductWindowHandle = accidentInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
            break;
            case APARTMENT: insuranceProductsPage.pressApartmentContainerButton();
                currentProductWindowHandle = propertyInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
            break;
            case TRAVEL: insuranceProductsPage.pressTravelContainerButton();
                currentProductWindowHandle = travelInsuranceTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
            break;
            case DMSSTANDARD:insuranceProductsPage.pressDmsContainerButton();
                currentProductWindowHandle = dmsTypesPage.getWindowHandle();
                TestListener.takeScreenshot();
            break;
        }
    }
}
