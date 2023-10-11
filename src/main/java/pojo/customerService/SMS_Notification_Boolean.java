package pojo.customerService;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SMS_Notification_Boolean {

    @JsonSetter("")
    private Boolean notificationStatus;
}