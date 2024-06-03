package pojo.cardService;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistryDebitBody {
    String type_name;
    String customer;
    String accountId;
    String currency;
    String paymentSystem;
    String deliveryType;
    String officeNumber;
}



