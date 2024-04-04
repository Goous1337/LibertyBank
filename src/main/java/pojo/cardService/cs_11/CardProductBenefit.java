package pojo.cardService.cs_11;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardProductBenefit {

    private String benefitType;
    private String title;
    private String description;
}