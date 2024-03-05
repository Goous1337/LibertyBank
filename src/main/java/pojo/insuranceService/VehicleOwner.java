package pojo.insuranceService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleOwner {
    private String name;
    private String surname;
    //
    private String patronym;
    private String birthdate;
    private String registrationAddress;
    private String actualAddress;

}
