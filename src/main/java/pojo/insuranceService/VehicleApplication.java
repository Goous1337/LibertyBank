package pojo.insuranceService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleApplication {
    private Integer insuranceProductId;
    private Integer currencyNumericCode;
    private Integer duration;
    private Boolean insurerIsOwner;
    private VehicleDocumentRequest vehicleDocumentRequest;
}
