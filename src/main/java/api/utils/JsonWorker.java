package api.utils;

import io.restassured.response.Response;
import pojo.insuranceService.InsuranceProductsOfBankResponseModel;

import java.util.ArrayList;
import java.util.List;

public class JsonWorker {
    public static List<String> getListOfResponseWithInsurancesProducts(Response response) {
        List<String> result = new ArrayList<>();
        List<InsuranceProductsOfBankResponseModel.DataEntry> dataEntries = response.getBody().
                as(InsuranceProductsOfBankResponseModel.class).getData();
        for (InsuranceProductsOfBankResponseModel.DataEntry entry : dataEntries) {
            for (InsuranceProductsOfBankResponseModel.DataEntry.Attributes.ProductsName.ProductData product :
                    entry.getAttributes().getProductsName().getProductData()) {
                result.add(product.getAttributes().getName());
            }
        }
        return result;
    }
}
