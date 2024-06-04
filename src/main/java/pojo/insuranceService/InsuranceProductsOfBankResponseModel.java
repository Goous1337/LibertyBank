package pojo.insuranceService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class InsuranceProductsOfBankResponseModel {
    private List<DataEntry> data;

    @Data
    public static class DataEntry {
        private int id;
        private Attributes attributes;

        @Data
        public static class Attributes {
            @JsonProperty("group_id")
            private int groupId;
            private String createdAt;
            private String updatedAt;
            private String publishedAt;
            @JsonProperty("Products_name")
            private ProductsName productsName;

            @Data
            public static class ProductsName {
                @JsonProperty("data")
                public List<ProductData> productData;

                @Data
                public static class ProductData {
                    public int id;
                    @JsonProperty("attributes")
                    public ProductAttributes attributes;

                    @Data
                    public static class ProductAttributes {
                        @JsonProperty("id_product")
                        public int idProduct;
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
