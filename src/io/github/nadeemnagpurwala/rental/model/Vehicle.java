package io.github.nadeemnagpurwala.rental.model;

import java.util.Objects;

public abstract class Vehicle {
    private static int idCounter = 1;
    private final int id;
    private final String brand;
    private final String model;
    private final double dailyRate;
    private VehicleAvailability vehicleAvailability = VehicleAvailability.AVAILABLE;
    private final FuelType fuelType;

    public Vehicle(String brand, String model, double dailyRate, FuelType fuelType) {
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.fuelType = fuelType;
        this.id = idCounter++;
    }

    public int getId() {
        return this.id;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public double getDailyRate() {
        return this.dailyRate;
    }

    public VehicleAvailability getVehicleAvailability() {
        return vehicleAvailability;
    }

    public void setVehicleAvailability(VehicleAvailability vehicleAvailability) {
        this.vehicleAvailability = vehicleAvailability;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public abstract double calculateRentalCost(int days);

    public double calculateSurcharge() {
        return getDailyRate() * getFuelType().getSurchargeRate();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", dailyRate=" + dailyRate +
                ", vehicleAvailability=" + vehicleAvailability +
                ", fuelType=" + fuelType +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        int objectId = ((Vehicle) obj).id;
        return this.id == objectId;
    }

    @Override
    public int hashCode() {
        //Two objects that are equal must return the same hash code.
        //equals() compares id and nothing else — so two vehicles are equal exactly when their ids match.
        return Objects.hash(id);
    }

    public void printDetails() {
        System.out.println("ID: " + getId());
        System.out.println("Brand: " + getBrand());
        System.out.println("Model: " + getModel());
        System.out.println("Daily rate: " + getDailyRate());
        System.out.println("Status: " + getVehicleAvailability());
        double percentage = this.getFuelType().getSurchargeRate() * 100;
        System.out.printf("Daily surcharge amount for this vehicle having fuel type %s in percentage : %.2f%% %n", this.getFuelType(), percentage);
        System.out.printf("Daily surcharge cost in INR : %.2f INR%n", calculateSurcharge());
    }
}
