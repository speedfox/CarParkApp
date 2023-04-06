package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarParkAppTest {
  private List<Car> parkedCars;
  private Car cloneClownCar;
  private CarParkApp carParkApp;

  @BeforeEach
  void setUp() {
    parkedCars = new ArrayList<>();
    carParkApp = new CarParkApp();
    cloneClownCar = new Car("Toyota", "Clownus");
  }

  @Test
  public void canCreateCar() {
    Car car = new Car("Test Make", "Test Model");
    assertEquals("Test Make", car.getCarMake());
    assertEquals("Test Model", car.getCarModel());
  }

  @Test
  void parkOneCars() {
    carParkApp.parkCar(cloneClownCar);
    assertEquals(1, carParkApp.getNumberOfParkedCars());
  }

  @Test
  void parkMultiplCars() {
    for (int t = 0; t < 10; t++) {
      carParkApp.parkCar(cloneClownCar);
    }
    assertEquals(10, carParkApp.getNumberOfParkedCars());
  }

  @Test
  void carLeaves() {
    carParkApp.parkCar(cloneClownCar);
    carParkApp.carLeaves();
    assertEquals(0, parkedCars.size());
  }

  @Test
  void calculateCharge() {
    Car car = new Car("Test", "Test");
    car.setTimeCarLeaves(Optional.of(LocalDateTime.now()));
    double charge = CarParkApp.calculateCharge(car);
    assertEquals(2, charge);
  }

  @Test
  void tryRemoveCarFromEmptyLot() {
    Optional<Car> car = carParkApp.carLeaves();
    assertFalse(car.isPresent());
  }

  @Test
  void calculateChargeFor3HoursParking() {
    Car car = new Car("Late Make", "Late Model");
    car.setTimeCarLeaves(Optional.of(LocalDateTime.now().plusHours(3)));
    double charge = CarParkApp.calculateCharge(car);
    assertEquals(6, charge);
  }
}
