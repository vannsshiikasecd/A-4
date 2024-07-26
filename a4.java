import java.util.Scanner;

public class a4{

    
    static class EnergyBill {
        
        private String customerName;
        private String address;
        private double electricityUsage; 
        private double gasUsage; 

        
        private static final double ELECTRICITY_RATE = 0.12; 
        private static final double GAS_RATE = 0.08; 
        
        public EnergyBill(String customerName, String address, double electricityUsage, double gasUsage) {
            this.customerName = customerName;
            this.address = address;
            this.electricityUsage = electricityUsage;
            this.gasUsage = gasUsage;
        }

       
        public double calculateCharges() {
            return (electricityUsage * ELECTRICITY_RATE) + (gasUsage * GAS_RATE);
        }

        
        public void displayBill() {
            System.out.println("Customer Name: " + customerName);
            System.out.println("Address: " + address);
            System.out.println("Electricity Usage: " + electricityUsage + " kWh");
            System.out.println("Gas Usage: " + gasUsage + " units");
            System.out.println("Total Charges: $" + String.format("%.2f", calculateCharges()));
        }
    }

    
    static class GreenEnergyBill extends EnergyBill {
        private double solarEnergyContribution; 

        
        private static final double SOLAR_DEDUCTION_RATE = 0.05; 

        
        public GreenEnergyBill(String customerName, String address, double electricityUsage, double gasUsage, double solarEnergyContribution) {
            super(customerName, address, electricityUsage, gasUsage);
            this.solarEnergyContribution = solarEnergyContribution;
        }

        
        @Override
        public double calculateCharges() {
            double initialCharges = super.calculateCharges();
            double solarDeduction = solarEnergyContribution * SOLAR_DEDUCTION_RATE;
            return Math.max(0, initialCharges - solarDeduction); 
        }

        
        @Override
        public void displayBill() {
            super.displayBill();
            System.out.println("Solar Energy Contribution: " + solarEnergyContribution + " kWh");
            System.out.println("Solar Energy Deduction: $" + String.format("%.2f", solarEnergyContribution * SOLAR_DEDUCTION_RATE));
            System.out.println("Total Bill After Solar Deduction: $" + String.format("%.2f", calculateCharges()));
        }
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        
        System.out.print("Enter electricity usage (kWh): ");
        double electricityUsage = scanner.nextDouble();

        System.out.print("Enter gas usage (units): ");
        double gasUsage = scanner.nextDouble();

        
        System.out.print("Do you use solar energy? (yes/no): ");
        String useSolar = scanner.next().trim().toLowerCase();

        if (useSolar.equals("yes")) {
            System.out.print("Enter solar energy contribution (kWh): ");
            double solarContribution = scanner.nextDouble();

            
            GreenEnergyBill greenBill = new GreenEnergyBill(name, address, electricityUsage, gasUsage, solarContribution);
            greenBill.displayBill();
        } else {
            
            EnergyBill bill = new EnergyBill(name, address, electricityUsage, gasUsage);
            bill.displayBill();
        }

        scanner.close();
    }
}
