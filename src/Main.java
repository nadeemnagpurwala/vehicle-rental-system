import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        RentalAgency rentalAgency = new RentalAgency();

        //Add vehicles to the fleet
        rentalAgency.addVehicle(new Bike(
                "Hero", "Xtreme 125R", 75.0, FuelType.PETROL
        ));
        rentalAgency.addVehicle(new Car(
                "Maruti", "WagonR", 100.0, FuelType.ELECTRIC
        ));
        rentalAgency.addVehicle(new Bike(
                "KTM", "390 Duke", 125.0, FuelType.ELECTRIC
        ));
        rentalAgency.addVehicle(new Car(
                "Hyundai", "Creta", 200.0, FuelType.PETROL
        ));
        rentalAgency.addVehicle(new Truck(
                "Mahindra", "Blazo", 300.0, FuelType.DIESEL
        ));

        //List the vehicles available for rent
        rentalAgency.displayVehicles();

        //User Input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select a vehicle id for rental purpose");
        if (scanner.hasNextInt()) {
            int vehicleId = scanner.nextInt();
            System.out.println();
            System.out.println("Please select number of days for which you want to rent the vehicle");
            if (scanner.hasNextInt()) {
                int numberOfDays = scanner.nextInt();
                rentalAgency.rentVehicle(vehicleId, numberOfDays);
                System.out.println();
                System.out.println("Please select a vehicle id you wish to return");
                if (scanner.hasNextInt()) {
                    int vehicleIdReturn = scanner.nextInt();
                    rentalAgency.returnVehicle(vehicleIdReturn);
                } else {
                    System.out.println("Please select a valid vehicle id");
                }
            } else {
                System.out.println("Please select valid numeric days");
            }
        } else {
            System.out.println("Please select a valid vehicle id");
        }
        scanner.close();
    }
}