package io.github.nadeemnagpurwala.rental.service;

import io.github.nadeemnagpurwala.rental.exception.VehicleNotAvailableException;
import io.github.nadeemnagpurwala.rental.exception.VehicleNotFoundException;
import io.github.nadeemnagpurwala.rental.model.Insurable;
import io.github.nadeemnagpurwala.rental.model.RentalReceipt;
import io.github.nadeemnagpurwala.rental.model.Vehicle;
import io.github.nadeemnagpurwala.rental.model.VehicleAvailability;

public class RentalAgency {
    private static final int MAX_FLEET_SIZE = 5;
    private Vehicle[] fleet = new Vehicle[MAX_FLEET_SIZE];
    private int count = 0;

    public void addVehicle(Vehicle vehicle){
        if (count >= MAX_FLEET_SIZE) {
            System.out.println("Maximum vehicles added, the fleet is currently full");
        } else {
            fleet[count] = vehicle;
            count++;
        }
    }

    public void displayVehicles() {
        if (count > 0) {
            System.out.println("We have following list of vehicles available in our fleet :");
            System.out.println();
            for (int i = 0; i < count; i++) {
                fleet[i].printDetails();
                if (fleet[i] instanceof Insurable insurable) {
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
        for (int i = 0; i < count; i++) {
            if (fleet[i].getId() == id) {
                return fleet[i];
            }
        }
        return null;
    }
}
