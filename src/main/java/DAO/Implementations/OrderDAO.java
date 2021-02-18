package DAO.Implementations;

import DAO.Interfaces.OrderInterface;
import Models.Order;
import Models.OrderDetails;
import Services.ClientService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO implements OrderInterface<Order> {
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";

    public OrderDAO() {

    }

    public void createOrder(Order order) {
        ResultSet resultSet = null;

        String sql = "insert into [Order] (ClientId, OrderDate, Amount, Tips, Tax, TotalAmount) values (?, ?, ?, ?, ?, ?)";

        String orderDetailsSql = "insert into OrderDetails (OrderID, DishID, Quantity) values (?,?,?)";

        int primaryKey = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try (PreparedStatement prepStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                prepStatement.setInt(1, order.getClient().getId());
                prepStatement.setDate(2, (Date) order.getDate());
                prepStatement.setDouble(3, order.getAmount());
                prepStatement.setDouble(4, order.getTips());
                prepStatement.setDouble(5, order.getTax());
                prepStatement.setDouble(6, order.getTotalAmount());
                prepStatement.executeUpdate();
                resultSet = prepStatement.getGeneratedKeys();
                if (resultSet != null && resultSet.next()) {
                    primaryKey = resultSet.getInt(1);
                }
            }

            try (PreparedStatement prepStatement = connection.prepareStatement(orderDetailsSql)){
                for (OrderDetails details : order.getOrderDetails()) {
                    prepStatement.setInt(1, primaryKey);
                    prepStatement.setInt(2, details.getDish().getId());
                    prepStatement.setInt(3, details.getQuantity());
                    prepStatement.executeUpdate();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Order> getAll() {

        ResultSet resultSet = null;

        List<Order> allOrders = new LinkedList<>();

        String sqlOrder = "select OrderID, ClientId, Amount, OrderDate, Tax, Tips, TotalAmount from [Order]";
        String sqlOrderDetails = "select [DishID], [OrderID], [Quantity] from OrderDetails where OrderID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try (PreparedStatement prepStatement = connection.prepareStatement(sqlOrder)) {
                resultSet = prepStatement.executeQuery();

                while (resultSet.next()) {
                    Order order = new Order();
                    ClientService clientService = new ClientService();

                    order.setId(resultSet.getInt("OrderID"));
                    order.setClient(clientService.getById(resultSet.getInt("ClientId")));
                    order.setAmount(resultSet.getDouble("Amount"));
                    order.setDate(resultSet.getDate("OrderDate"));
                    order.setTax(resultSet.getDouble("Tax"));
                    order.setTips(resultSet.getDouble("Tips"));
                    order.setTotalAmount(resultSet.getDouble("TotalAmount"));

                    allOrders.add(order);
                }
            }

            try (PreparedStatement prepStatement = connection.prepareStatement(sqlOrderDetails)){
                for (Order order : allOrders) {
                    int id = order.getId();
                    List<OrderDetails> orderDetailsList = new LinkedList<>();
                    DishDAO dishDAO = new DishDAO();

                    prepStatement.setInt(1, id);
                    resultSet = prepStatement.executeQuery();


                    while (resultSet.next()){
                        int dishId = resultSet.getInt("DishID");
                        orderDetailsList.add(new OrderDetails(dishDAO.getById(dishId), resultSet.getInt("Quantity")));
                    }
                    order.setOrderDetails(orderDetailsList);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allOrders;
    }

    public double calculateAverageCheck() {
        String sql = "select sum(TotalAmount) / count(OrderID) from [Order]";
        double result = 0;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             ResultSet resultSet = prepStatement.executeQuery()){
            if (resultSet.next()) {
                result = resultSet.getDouble(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public double calculateCheckDepth() {
        String sql = "select cast(sum(OrderDetails.Quantity) as float) / cast(count(distinct [Order].OrderID) as float) from OrderDetails \n" +
                "full outer join [Order] on [Order].OrderID = OrderDetails.OrderIDz";
        double result = 0;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             ResultSet resultSet = prepStatement.executeQuery()){
            if (resultSet.next()) {
                result = resultSet.getDouble(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
