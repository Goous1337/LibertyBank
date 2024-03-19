package pojo.insuranceService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleApplicationInsuranceRequest {
  private String type;
  private VehicleApplication vehicleApplication;
}
