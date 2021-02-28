import Models.Client;
import Services.CategoryServices;
import Services.ClientService;
import Services.OrderService;
import Services.UnitService;

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
            System.out.println("Press 1 to add a client");
            System.out.println("Press 2 to update a client");
            System.out.println("Press 3 to delete a client");
            System.out.println("Press enter to exit");

            choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("enter name");
                    String clientNameCreate = scan.nextLine();
                    System.out.println("enter surname");
                    String clientSurnameCreate = scan.nextLine();
                    System.out.println("enter phone");
                    String clientPhoneCreate = scan.nextLine();
                    System.out.println("enter discount");
                    double clientDiscountCreate = scan.nextDouble();
                    scan.nextLine();
                    Client clientCreate = new Client(clientSurnameCreate, clientNameCreate, clientPhoneCreate, clientDiscountCreate);
                    ClientService clientServiceCreate = new ClientService();
                    clientServiceCreate.create(clientCreate);
                    System.out.println("client has been successfully created");
                    break;
                case "2":
                    System.out.println("Enter client id");
                    int clientIdUpdate = scan.nextInt();
                    scan.nextLine();
                    System.out.println("enter name");
                    String clientNameUpdate = scan.nextLine();
                    System.out.println("enter surname");
                    String clientSurnameUpdate = scan.nextLine();
                    System.out.println("enter phone");
                    String clientPhoneUpdate = scan.nextLine();
                    System.out.println("enter discount");
                    double clientDiscountUpdate = scan.nextDouble();
                    scan.nextLine();
                    ClientService clientServiceUpdate = new ClientService();
                    Client clientUpdate = new Client(clientSurnameUpdate, clientNameUpdate, clientPhoneUpdate, clientDiscountUpdate);
                    clientServiceUpdate.update(clientUpdate, clientIdUpdate);
                    System.out.println("Client has been successfully updated");
                    break;

                case "3":
                    System.out.println("Enter client id");
                    int clientIdDelete = scan.nextInt();
                    scan.nextLine();
                    ClientService clientServiceDelete = new ClientService();
                    clientServiceDelete.delete(clientIdDelete);
                    System.out.println("Client has been successfully deleted");
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
