package org.example.models;

import lombok.Data;

@Data
public class Car extends Vehicle{
    private String carMake;
    private String carModel;
    public Car(String carMake, String carModel) {
        super();
        this.carMake = carMake;
        this.carModel = carModel;
    }
}
