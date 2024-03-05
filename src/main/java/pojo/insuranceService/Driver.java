package pojo.insuranceService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Driver {
    private String name;
    private String surname;
    //
    private String patronym;
    private String birthdate;
    private String licenseId;
    private String licenseIssuingDate;
    private String firstLicenseIssuingDate;
}
