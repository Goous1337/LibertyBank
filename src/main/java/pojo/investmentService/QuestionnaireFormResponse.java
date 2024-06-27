package pojo.investmentService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireFormResponse {
    public String lastName;
    public String firstName;
    public String middleName;
    public int birthDate;
    public String citizenship;
    public String series;
    public String number;
    public String issuedBy;
    public String departmentCode;
    public int dateOfIssue;
    public String region;
    public String street;
    public String houseNumber;
    public String apartmentNumber;
    public String inn;
    public String mobilePhone;
    public String email;
    private Boolean residence;
    private Boolean abroadTax;
    private Boolean beneficialOwner;
    private Boolean representative;
    private Boolean beneficiary;
}