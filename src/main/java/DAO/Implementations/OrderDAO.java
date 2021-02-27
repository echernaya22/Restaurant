package DAO.Implementations;

import DAO.Interfaces.OrderInterface;
import Models.Order;
import Models.OrderDetails;
import Services.ClientService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO implements OrderInterface<Order> {
    private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";
    private static final Logger log = Logger.getLogger(OrderDAO.class);
    public OrderDAO() {

    }

    public void createOrder(Order order) {
        ResultSet resultSet;

        String orderSql = "insert into [Order] (ClientId, OrderDate, Amount, Tips, Tax, TotalAmount) values (?, ?, ?, ?, ?, ?)";

        String orderDetailsSql = "insert into OrderDetails (OrderID, DishID, Quantity) values (?,?,?)";

        int primaryKey = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try (PreparedStatement prepStatement = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)){
                prepStatement.setLong(1, order.getClient().getId());
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
                    prepStatement.setLong(1, primaryKey);
                    prepStatement.setLong(2, details.getDish().getId());
                    prepStatement.setInt(3, details.getQuantity());
                    prepStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in OrderDAO.createOrder: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in OrderDAO.createOrder: ", e);
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

                    order.setId(resultSet.getLong("OrderID"));
                    order.setClient(clientService.getById(resultSet.getLong("ClientId")));
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
                    long id = order.getId();
                    List<OrderDetails> orderDetailsList = new LinkedList<>();
                    DishDAO dishDAO = new DishDAO();

                    prepStatement.setLong(1, id);
                    resultSet = prepStatement.executeQuery();


                    while (resultSet.next()){
                        long dishId = resultSet.getLong("DishID");
                        orderDetailsList.add(new OrderDetails(dishDAO.getById(dishId), resultSet.getInt("Quantity")));
                    }
                    order.setOrderDetails(orderDetailsList);
                }
            }

        } catch (SQLException e) {
            log.error("SQLException is caught in OrderDAO.getAll: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in OrderDAO.getAll: ", e);
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

        } catch (SQLException e) {
            log.error("SQLException is caught in OrderDAO.calculateAverageCheck: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in OrderDAO.calculateAverageCheck: ", e);
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

        } catch (SQLException e) {
            log.error("SQLException is caught in OrderDAO.calculateCheckDepth: ", e);
        } catch (Exception e) {
            log.error("Exception is caught in OrderDAO.calculateCheckDepth: ", e);
        }
        return result;
    }
}
