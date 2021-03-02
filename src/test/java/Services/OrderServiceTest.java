package Services;

import DAO.Implementations.OrderDAO;
import Models.*;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderServiceTest {
    private Connection connection;

    private OrderService orderService = new OrderService();


    public OrderServiceTest() {

    }

    @BeforeClass
    public void before() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createOrder() throws SQLException {
        ClientService clientService = new ClientService();
        Client client = clientService.getById(2);

        DishService dishService = new DishService();
        Dish dish = dishService.getById(1);

        OrderDetails orderDetails = new OrderDetails(dish, 2);
        List<OrderDetails> orderDetailsList = new LinkedList<>();
        orderDetailsList.add(orderDetails);
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Order order = new Order(client, sqlDate, orderDetailsList);

        long expectedKey = orderService.createOrder(order);

        order.setId(expectedKey);

        Assert.assertEquals(order, orderService.getById(expectedKey));

    }

    @Test
    public void getAllOrders() throws SQLException {

        ClientService clientService = new ClientService();
        Client client = clientService.getById(2);

        DishService dishService = new DishService();
        Dish dish = dishService.getById(1);

        OrderDetails orderDetails = new OrderDetails(dish, 2);
        List<OrderDetails> orderDetailsList = new LinkedList<>();
        orderDetailsList.add(orderDetails);
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Order order = new Order(client, sqlDate, orderDetailsList);

        long expectedKey = orderService.createOrder(order);

        order.setId(expectedKey);

        List<Order> orderList;
        orderList = orderService.getAll();

        Assert.assertTrue(orderList.contains(order));

    }

    @Test
    public void calculateAverageCheck() throws SQLException{

    }


}
