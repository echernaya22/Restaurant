import Models.*;
import Services.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Restaurant {
    public static void main(String[] args) throws SQLException {

        Scanner scan = new Scanner(System.in);
        String choice = "0";

        while (!choice.trim().equals("")) {
            System.out.println("Press 1 to enter as admin or 2 to enter as user");
            choice = scan.nextLine();
            if (choice.equals("1")) {
                adminMenu();
            } else if (choice.equals("2")) {
                userMenu();
            } else {
                System.exit(0);
            }
        }

    }



    public static void userMenu() throws SQLException {
        Scanner scan = new Scanner(System.in);
        String choice = "";
        System.out.println("USER menu");
        while (!choice.equals("4")) {
            System.out.println("Press 1 to create order");
            System.out.println("Press 2 to get menu");
            System.out.println("Press 3 to get menu by category");
            System.out.println("Press 4 to exit");

            choice = scan.nextLine();

            switch (choice) {
                case "1":
                    ClientService clientService = new ClientService();

                    System.out.println("Enter your phone number");
                    String clientPhoneNumber = scan.nextLine();

                    Client client = clientService.getByPhoneNumber(clientPhoneNumber);

                    List<OrderDetails> orderDetailsList = new LinkedList<>();

                    System.out.println("Enter dish ID");
                    long dishId = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter quantity");
                    int dishQuantity = scan.nextInt();
                    scan.nextLine();

                    DishService dishService = new DishService();
                    Dish dish = dishService.getById(dishId);
                    OrderDetails orderDetails = new OrderDetails(dish, dishQuantity);
                    orderDetailsList.add(orderDetails);

                    String moreOrderDetails;
                    System.out.println("One more dish? (y/n)");
                    moreOrderDetails = scan.nextLine();

                    while (moreOrderDetails.equals("y")) {
                        System.out.println("Enter one more dish and quantity or press enter");
                        dishId = scan.nextInt();
                        scan.nextLine();
                        dishQuantity = scan.nextInt();
                        scan.nextLine();
                        dish = dishService.getById(dishId);
                        orderDetails = new OrderDetails(dish, dishQuantity);
                        orderDetailsList.add(orderDetails);

                        System.out.println("One more dish? (y/n)");
                        moreOrderDetails = scan.nextLine();
                    }

                    Date date = new Date();
                    OrderService orderService = new OrderService();
                    Order order = new Order(client, date, orderDetailsList);

                    System.out.println("Enter tips");
                    double tips = scan.nextDouble();
                    scan.nextLine();
                    order.setTips(tips);

                    orderService.createOrder(order);

                    System.out.println("Total amount of your order: " + order.getTotalAmount());

                    System.out.println("Order is created");
                    break;
                case "2":
                    DishService dishServiceGetAll = new DishService();
                    System.out.println(dishServiceGetAll.getAll());
                    break;
                case "3":
                    DishService dishServiceGetByCategory = new DishService();
                    System.out.println("Enter category name");
                    String categoryName = scan.nextLine();
                    System.out.println(dishServiceGetByCategory.getByCategory(categoryName));
                    break;
                default:
                    choice = "4";
            }
        }
    }

    public static void adminMenu() throws SQLException {
        Scanner scan = new Scanner(System.in);
        String choice = "";
        System.out.println("ADMIN menu");
        while (!choice.equals("21")) {
            System.out.println("*************** CLIENT ***************");
            System.out.println("Press 1 to add client");
            System.out.println("Press 2 to update client");
            System.out.println("Press 3 to delete client");
            System.out.println("Press 4 to get list of clients");
            System.out.println("Press 5 to get client by phone number");
            System.out.println("Press 6 to get client by ID \n");

            System.out.println("*************** DISH ***************");
            System.out.println("Press 7 to create dish");
            System.out.println("Press 8 to update dish");
            System.out.println("Press 9 to delete dish \n");

            System.out.println("*************** CATEGORY ***************");
            System.out.println("Press 10 to create category");
            System.out.println("Press 11 to update category");
            System.out.println("Press 12 to delete category");
            System.out.println("Press 13 to get list of categories \n");

            System.out.println("*************** UNIT ***************");
            System.out.println("Press 14 to create unit");
            System.out.println("Press 15 to update unit");
            System.out.println("Press 16 to delete unit");
            System.out.println("Press 17 to get list of unit \n");

            System.out.println("*************** ORDER ***************");
            System.out.println("Press 18 to get list of orders");
            System.out.println("Press 19 to get average check");
            System.out.println("Press 20 to get check depth");
            System.out.println("Press 21 to exit");

            choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter name");
                    String clientNameCreate = scan.nextLine();
                    System.out.println("Enter surname");
                    String clientSurnameCreate = scan.nextLine();
                    System.out.println("Enter phone");
                    String clientPhoneCreate = scan.nextLine();
                    System.out.println("Enter discount");
                    double clientDiscountCreate = scan.nextDouble();
                    scan.nextLine();
                    Client clientCreate = new Client(clientSurnameCreate, clientNameCreate, clientPhoneCreate, clientDiscountCreate);
                    ClientService clientServiceCreate = new ClientService();
                    clientServiceCreate.create(clientCreate);
                    System.out.println("Client has been successfully created");
                    break;
                case "2":
                    System.out.println("Enter client id");
                    int clientIdUpdate = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter name");
                    String clientNameUpdate = scan.nextLine();
                    System.out.println("Enter surname");
                    String clientSurnameUpdate = scan.nextLine();
                    System.out.println("Enter phone");
                    String clientPhoneUpdate = scan.nextLine();
                    System.out.println("Enter discount");
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
                case "4":
                    ClientService clientServiceGetAll = new ClientService();
                    System.out.println(clientServiceGetAll.getAll());
                    break;
                case "5":
                    ClientService clientServiceGetByPhoneNumber = new ClientService();
                    System.out.println("Enter a phone number");
                    String clientPhoneNumber = scan.nextLine();
                    System.out.println(clientServiceGetByPhoneNumber.getByPhoneNumber(clientPhoneNumber));
                    break;
                case "6":
                    ClientService clientServiceGetById = new ClientService();
                    System.out.println("Enter client id");
                    long clientIdGet = scan.nextLong();
                    scan.nextLine();
                    System.out.println(clientServiceGetById.getById(clientIdGet));
                    break;
                case "7":
                    DishService dishServiceCreate = new DishService();
                    CategoryServices categoryServicesCreateDish = new CategoryServices();
                    UnitService unitServiceCreateDish = new UnitService();
                    System.out.println("Enter dish name");
                    String dishNameCreate = scan.nextLine();
                    System.out.println("Enter price");
                    double dishPriceCreate = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter weight");
                    double dishWeightCreate = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter category ID");
                    long categoryNameCreateDish = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter unit ID");
                    long unitNameCreateDish = scan.nextInt();
                    scan.nextLine();
                    Dish dishCreate = new Dish(dishNameCreate, categoryServicesCreateDish.getById(categoryNameCreateDish),
                            dishPriceCreate, dishWeightCreate, unitServiceCreateDish.getById(unitNameCreateDish));
                    dishServiceCreate.create(dishCreate);
                    System.out.println("Dish has bee successfully created");
                    break;
                case "8":
                    DishService dishServiceUpdate = new DishService();
                    CategoryServices categoryServicesUpdateDish = new CategoryServices();
                    UnitService unitServiceUpdateDish = new UnitService();
                    System.out.println("Enter dish name");
                    String dishNameUpdate = scan.nextLine();
                    System.out.println("Enter price");
                    double dishPriceUpdate = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter weight");
                    double dishWeightUpdate = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter category ID");
                    long categoryNameUpdateDish = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter unit ID");
                    long unitNameUpdateDish = scan.nextInt();
                    scan.nextLine();
                    Dish dishUpdate = new Dish(dishNameUpdate, categoryServicesUpdateDish.getById(categoryNameUpdateDish),
                            dishPriceUpdate, dishWeightUpdate, unitServiceUpdateDish.getById(unitNameUpdateDish));
                    System.out.println("Enter dish ID you want to update");
                    long dishIdUpdate = scan.nextInt();
                    scan.nextLine();
                    dishServiceUpdate.update(dishUpdate, dishIdUpdate);
                    System.out.println("Dish has bee successfully updated");
                    break;
                case "9":
                    DishService dishServiceDelete = new DishService();
                    System.out.println("Enter dish ID you want to delete");
                    long dishIdDelete = scan.nextLong();
                    scan.nextLine();
                    dishServiceDelete.delete(dishIdDelete);
                    System.out.println("Dish has bee successfully deleted");
                    break;
                case "10":
                    CategoryServices categoryServicesCreate = new CategoryServices();
                    System.out.println("Enter category name");
                    String categoryNameCreate = scan.nextLine();
                    Category categoryCreate = new Category(categoryNameCreate);
                    categoryServicesCreate.create(categoryCreate);
                    System.out.println("Category has bee successfully created");
                    break;
                case "11":
                    CategoryServices categoryServicesUpdate = new CategoryServices();
                    System.out.println("Enter category name");
                    String categoryNameUpdate = scan.nextLine();
                    Category categoryUpdate = new Category(categoryNameUpdate);
                    System.out.println("Enter category ID ypu want to update");
                    long categoryIdUpdate = scan.nextLong();
                    scan.nextLine();
                    categoryServicesUpdate.update(categoryUpdate, categoryIdUpdate);
                    System.out.println("Category has bee successfully updated");
                    break;
                case "12":
                    CategoryServices categoryServicesDelete = new CategoryServices();
                    System.out.println("Enter category ID you want to delete");
                    long categoryIdDelete = scan.nextLong();
                    scan.nextLine();
                    categoryServicesDelete.delete(categoryIdDelete);
                    System.out.println("Category has bee successfully deleted");
                    break;
                case "13":
                    CategoryServices categoryServicesGetAll = new CategoryServices();
                    System.out.println(categoryServicesGetAll.getAll());
                    break;
                case "14":
                    UnitService unitServiceCreate = new UnitService();
                    System.out.println("Enter unit name");
                    String unitNameCreate = scan.nextLine();
                    Unit unitCreate = new Unit(unitNameCreate);
                    unitServiceCreate.create(unitCreate);
                    System.out.println("Unit has bee successfully created");
                    break;
                case "15":
                    UnitService unitServiceUpdate = new UnitService();
                    System.out.println("Enter unit name");
                    String unitNameUpdate = scan.nextLine();
                    Unit unitUpdate = new Unit(unitNameUpdate);
                    System.out.println("Enter unit ID ypu want to update");
                    long unitIdUpdate = scan.nextLong();
                    scan.nextLine();
                    unitServiceUpdate.update(unitUpdate, unitIdUpdate);
                    System.out.println("Unit has bee successfully updated");
                    break;
                case "16":
                    UnitService unitServiceDelete = new UnitService();
                    System.out.println("Enter unit ID you want to delete");
                    long unitIdDelete = scan.nextLong();
                    scan.nextLine();
                    unitServiceDelete.delete(unitIdDelete);
                    System.out.println("Unit has bee successfully deleted");
                    break;
                case "17":
                    UnitService unitServiceGetAll = new UnitService();
                    System.out.println(unitServiceGetAll.getAll());
                    break;
                case "18":
                    OrderService orderServiceGetAll = new OrderService();
                    System.out.println(orderServiceGetAll.getAll());
                    break;
                case "19":
                    OrderService orderServiceAverageCheck = new OrderService();
                    System.out.println("Average check: " + orderServiceAverageCheck.calculateAverageCheck());
                    break;
                case "20":
                    OrderService orderServiceCheckDepth = new OrderService();
                    System.out.println("Check depth: " + orderServiceCheckDepth.calculateCheckDepth());
                    break;
                default:
                    choice = "21";
            }
        }
    }

}
