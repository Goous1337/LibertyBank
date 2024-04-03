package pojo.cardService;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrDebitBody {
    String account;
    String customer;
    String productType;
    Boolean favourite;
}



