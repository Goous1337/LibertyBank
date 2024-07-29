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
    private String city;
    private String street;
    private String building;
    private String apartment;
    private String floor;
    private String entrance;
    private String type;
}
