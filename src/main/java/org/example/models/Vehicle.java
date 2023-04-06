package org.example.models;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Data;

@Data
public abstract class Vehicle {
    private double totalCharge = 0;
    private double totalTime = 0;
    private LocalDateTime timeCarParked = LocalDateTime.now(); //Default timeParked to object creation time+
    private Optional<LocalDateTime> timeCarLeaves;
}
