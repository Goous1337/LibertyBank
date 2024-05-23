package pojo.insuranceService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CarInsuranceResponseModel {
    private List<DataEntry> data;

    public static List<String> getListOfProductsNameFromResponse(CarInsuranceResponseModel carInsuranceResponseModel) {
        List<String> result = new ArrayList<>();
        List<CarInsuranceResponseModel.DataEntry> dataEntries = carInsuranceResponseModel.getData();
        for (CarInsuranceResponseModel.DataEntry entry : dataEntries) {
            for (CarInsuranceResponseModel.DataEntry.Attributes.ProductsName.ProductData product : entry.getAttributes().getProductsName().getProductData()) {
                result.add(product.getAttributes().getName());
            }
        }
        return result;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataEntry {
        private int id;
        private Attributes attributes;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Attributes {
            private int group_id;
            private String createdAt;
            private String updatedAt;
            private String publishedAt;
            @JsonProperty("Products_name")
            private ProductsName productsName;

            @JsonIgnoreProperties(ignoreUnknown = true)
            @Data
            public static class ProductsName {
                @JsonProperty("data")
                public List<ProductData> productData;

                @Data
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class ProductData {
                    public int id;
                    @JsonProperty("attributes")
                    public ProductAttributes attributes;

                    @Data
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class ProductAttributes {
                        public int id_product;
                        public String name;
                        public String createdAt;
                        public String updatedAt;
                        public String publishedAt;
                    }
                }
            }
        }
    }
}
