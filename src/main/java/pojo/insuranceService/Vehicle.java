package pojo.insuranceService;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Vehicle {
    ArrayList<Driver> drivers;
    private String vehicleType;
    private String model;
    private String brand;
    private Integer productionDate;
    private String numberPlate;
    private Integer price;
    //
    private String vinCode;
    //
    private String bodyCode;
    //
    private String chassisCode;
    private Integer power;
    private Integer mileage;
}
