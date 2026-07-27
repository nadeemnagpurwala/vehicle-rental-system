public class Car extends Vehicle implements Insurable{
    private static final double insuranceRatePercent = 0.15;

    public Car(String brand, String model, double dailyRate) {
        super(brand, model, dailyRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        double baseRate = this.getDailyRate() * days;
        double insuranceAmount = calculateInsurance();
        double totalInsuranceAmount = insuranceAmount * days;
        return baseRate + totalInsuranceAmount;
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
