package Services;

import DAO.Implementations.OrderDAO;
import Models.Order;
import Models.OrderDetails;

import java.util.List;

public class OrderService {
    private OrderDAO orderDb = new OrderDAO();

    public OrderService() {

    }

    public void createOrder(Order order) {
        double amount = 0;

        for(OrderDetails detail : order.getOrderDetails()) {
            amount += detail.getDish().getPrice() * detail.getQuantity();
        }

        double discount = order.getClient().getDiscount();
        if (discount > 0) amount -= amount * discount / 100;

        double tax = amount * 0.06;

        order.setAmount(amount);
        order.setTax(tax);
        order.setTotalAmount(amount+tax);

        orderDb.createOrder(order);
    }

    public List<Order> getAll() {
        return orderDb.getAll();
    }

    public double calculateAverageCheck() {
        return orderDb.calculateAverageCheck();
    }

    public double calculateCheckDepth() {
        return orderDb.calculateCheckDepth();
    }
}
