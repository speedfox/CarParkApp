package org.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.models.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarParkApp {
    //List is not synchronised however vector is old and slow, so recommend usage of
    // Stream collect functionality to avoid collisions
  private final List<Car> parkedCars = new ArrayList<>(); // Instantiate to 0 size for not null
  private static final int maxNumCars = 100;
  private static final Logger logger = LoggerFactory.getLogger(CarParkApp.class);
  public static final double chargePerHour = 2;

  public static void main(String[] args) {
    CarParkApp carParkApp = new CarParkApp();
    Car cloneClownCar = new Car("Toyota", "Clownus");
    logger.info(carParkApp.parkCar(cloneClownCar));
    Optional<Car> car = carParkApp.carLeaves();
    logger.info("Charge for car leaving: " + calculateCharge(car.get()));
  }

  /**
   * Simple method to park some cars, synchronise it so that it can be called with multithreading
   *
   * @param car car to park
   */
  public synchronized String parkCar(Car car) {
    if (parkedCars.size() == maxNumCars) {
      return "Car park is full, number of cars parked: " + parkedCars.size();
    } else {
        parkedCars.add(car);
        return "Car parked";
    }
  }

  /**
   * Simple synchronised method to remove car and return number of hours stayed
   *
   * @return Number of hours stayed
   */
  public synchronized Optional<Car> carLeaves() {
    if (parkedCars.size() > 0) {
      Car departingCar = parkedCars.remove(0); // Remove first car
      departingCar.setTimeCarLeaves(Optional.of(LocalDateTime.now()));
      return Optional.of(departingCar);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Calculate charge for car, minimum of 1 hour
   *
   * @param car
   * @return charge for hour
   */
  public static double calculateCharge(Car car) {
    long timeParked = ChronoUnit.HOURS.between(car.getTimeCarParked(), car.getTimeCarLeaves().get());
    if (timeParked < 1) {
      timeParked = 1; // Default to at least 1 hour of time
    }
    return timeParked * chargePerHour;
  }

  /**
   * For use by external classes to get parkedCars size
   * @return
   */
  public int getNumberOfParkedCars(){
    return parkedCars.size();
  }
}
