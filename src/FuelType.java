public enum FuelType {
    PETROL(0.10), DIESEL(0.08), ELECTRIC(0.05);

    private final double surchargeRate;

    FuelType(double surchargeRate) {
        this.surchargeRate = surchargeRate;
    }

    public double getSurchargeRate() {
        return surchargeRate;
    }
}
