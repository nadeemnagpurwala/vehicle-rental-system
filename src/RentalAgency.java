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

    public void rentVehicle(int id, int days) {
        Vehicle vehicle = findVehicleById(id);
        if (vehicle == null) {
            System.out.println("Vehicle not found with the provided id");
        } else if (vehicle.isRented()) {
            System.out.println("The selected vehicle is not available for rent");
        } else {
            vehicle.setRented(true);
            System.out.printf("You have rented %s %s for %d days at %.2f INR %n", vehicle.getBrand(), vehicle.getModel(), days, vehicle.calculateRentalCost(days));
        }
    }

    public void returnVehicle(int id) {
        Vehicle vehicle = findVehicleById(id);
        if (vehicle == null) {
            System.out.println("Vehicle not found with the provided id");
        } else if (!vehicle.isRented()) {
            System.out.println("The selected vehicle has not been rented, hence can't be returned");
        } else {
            vehicle.setRented(false);
            System.out.printf("You have returned %s %s %n", vehicle.getBrand(), vehicle.getModel());
        }
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
