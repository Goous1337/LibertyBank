package pojo.depositService;


import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DepositCheckEmail {

    @JsonSetter("email")
    private String email;
    private Integer id;
}
