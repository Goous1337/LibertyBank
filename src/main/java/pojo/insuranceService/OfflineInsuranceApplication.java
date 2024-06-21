package pojo.insuranceService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfflineInsuranceApplication {
    private String insuranceProductId;
    private String name;
    private String surname;
    private String patronym;
    private long mobilePhone;
    private String date;
    private String time;
    private String address;
    private String officeNumber;
    private String type;
}
