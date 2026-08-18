package io.github.nadeemnagpurwala.rental.service;

import io.github.nadeemnagpurwala.rental.exception.*;
import io.github.nadeemnagpurwala.rental.model.*;
import java.util.List;
import java.util.ArrayList;

public class RentalAgency {
    private List<Vehicle> fleet = new ArrayList<>();

    public void addVehicle(Vehicle vehicle){
        fleet.add(vehicle);
    }

    public void displayVehicles() {
        if (!fleet.isEmpty()) {
            System.out.println("We have following list of vehicles available in our fleet :");
            System.out.println();
            for (Vehicle vehicleFleet : fleet) {
                vehicleFleet.printDetails();
                if (vehicleFleet instanceof Insurable insurable) {
                    System.out.printf("Daily insurance cost in INR : %.2f INR%n", insurable.calculateInsurance());
                }
                System.out.println("*".repeat(20));
            }
        } else {
            System.out.println("There are no vehicles available in our fleet");
        }
    }

    public RentalReceipt rentVehicle(int id, int days) throws VehicleNotFoundException, VehicleNotAvailableException {
        Vehicle vehicle = findVehicleById(id);
        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle not found with the provided id");
        }
        if (vehicle.getVehicleAvailability() == VehicleAvailability.RENTED) {
            throw new VehicleNotAvailableException("The selected vehicle is not available for rent");
        }
        vehicle.setVehicleAvailability(VehicleAvailability.RENTED);
        return new RentalReceipt(vehicle.getBrand(), vehicle.getModel(), vehicle.calculateRentalCost(days), days);
    }

    public void returnVehicle(int id) throws VehicleNotFoundException, VehicleNotAvailableException {
        Vehicle vehicle = findVehicleById(id);
        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle not found with the provided id");
        }
        if (vehicle.getVehicleAvailability() == VehicleAvailability.AVAILABLE) {
            throw new VehicleNotAvailableException("The selected vehicle has not been rented, hence can't be returned");
        }
        vehicle.setVehicleAvailability(VehicleAvailability.AVAILABLE);
        System.out.printf("You have returned %s %s %n", vehicle.getBrand(), vehicle.getModel());
    }

    private Vehicle findVehicleById(int id) {
        for (Vehicle vehicleFleet : fleet) {
            if (vehicleFleet.getId() == id) {
                return vehicleFleet;
            }
        }
        return null;
    }
}
