package pojo.insuranceService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDocumentRequest {
    private VehicleOwner vehicleOwner;
    private Vehicle vehicle;
    private String vehicleDocumentType;
    private String number;
    private String issuingDate;
}
