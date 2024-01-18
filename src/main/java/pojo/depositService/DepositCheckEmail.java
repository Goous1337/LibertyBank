package pojo.depositService;


import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DepositCheckEmail {

    @JsonSetter("eMail")
    private String eMail;
    private Integer id;
}
