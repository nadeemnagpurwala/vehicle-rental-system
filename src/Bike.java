public class Bike extends Vehicle{
    private static final double discountFeesPercent = 0.1;
    private static final int minimumDaysRequiredForDiscount = 7;

    public Bike(String brand, String model, double dailyRate) {
        super(brand, model, dailyRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        double rentalCostAmount = this.getDailyRate() * days;
        if (days >= minimumDaysRequiredForDiscount) {
            rentalCostAmount = discountAmountApplied(rentalCostAmount);
        }
        return rentalCostAmount;
    }

    private double discountAmountApplied(double amountBeforeDiscount) {
        return amountBeforeDiscount - (amountBeforeDiscount * discountFeesPercent);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        double percentage = discountFeesPercent * 100;
        System.out.printf("Additional discount of %.2f%% if you book a bike for at least %d days %n", percentage, minimumDaysRequiredForDiscount);
    }
}
