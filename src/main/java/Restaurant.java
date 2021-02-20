import Models.Client;
import Services.ClientService;
import Services.OrderService;

import java.sql.SQLException;
import java.util.Scanner;

public class Restaurant {
    public static void main(String[] args) throws SQLException {

        Scanner scan = new Scanner(System.in);
        System.out.println("1 = admin, 2 = user");
        int choice = scan.nextInt();

        if (choice == 1) {
            adminMenu();
        } else if (choice == 2) {
            userMenu();
        } else {
            System.out.println("eeee");
        }


    }



    public static void userMenu() {
        Scanner scan = new Scanner(System.in);
        String choice = "";
        System.out.println("USER");
        while (choice != "3") {
            System.out.println("Press 1 to enter as user");
            System.out.println("Press 2 to enter as admin");
            System.out.println("Press enter to exit");

            choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("you choose 1");
                    break;
                case "2":
                    System.out.println("few");
                    break;
                default:
                    choice = "3";
            }
        }
    }

    public static void adminMenu() throws SQLException {
        Scanner scan = new Scanner(System.in);
        String choice = "";
        System.out.println("ADMIN");
        while (choice != "3") {
            System.out.println("Press 1 to enter as user");
            System.out.println("Press 2 to enter as admin");
            System.out.println("Press enter to exit");

            choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("enter name");
                    String clientName = scan.nextLine();
                    System.out.println("enter surname");
                    String clientSurname = scan.nextLine();
                    System.out.println("enter phone");
                    String clientPhone = scan.nextLine();
                    System.out.println("enter discount");
                    double clientDiscount = scan.nextDouble();
                    Client client = new Client(clientSurname, clientName, clientPhone, clientDiscount);
                    ClientService clientService = new ClientService();
                    clientService.create(client);
                    System.out.println("client has been successfully created");
                    break;
                case "2":
                    System.out.println("All clients");
                    ClientService clientService1 = new ClientService();
                    System.out.println(clientService1.getAll());
                    break;
                default:
                    choice = "3";
            }
        }
    }

    public enum Role {
        ADMIN (1),
        USER (2);

        private final int roleCode;

        private Role(int roleCode) {
            this.roleCode = roleCode;
        }
    }
}
