package io.github.nadeemnagpurwala.rental.model;

public class Truck extends Vehicle implements Insurable {
    private static final double insuranceRatePercent = 0.25;

    public Truck(String brand, String model, double dailyRate, FuelType fuelType) {
        super(brand, model, dailyRate, fuelType);
    }

    @Override
    public double calculateRentalCost(int days) {
        double baseRate = this.getDailyRate() * days;
        double insuranceAmount = calculateInsurance();
        double totalInsuranceAmount = insuranceAmount * days;
        double totalSurchargeAmount = calculateSurcharge() * days;
        return baseRate + totalInsuranceAmount + totalSurchargeAmount;
    }

    @Override
    public double calculateInsurance() {
        return getDailyRate() * insuranceRatePercent;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        double percentage = insuranceRatePercent * 100;
        System.out.printf("Daily insurance amount in percentage : %.2f%% %n", percentage);
    }
}
