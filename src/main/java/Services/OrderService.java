package Services;

import DAO.Implementations.OrderDAO;
import Models.Order;
import Models.OrderDetails;

import java.util.List;

public class OrderService {
    private OrderDAO orderDb = new OrderDAO();

    public OrderService() {

    }

    private double getTotalAmount(Order order) {
        double amount = 0;
        for(OrderDetails detail : order.getOrderDetails()) {
            amount += detail.getDish().getPrice() * detail.getQuantity();
        }

        double discount = order.getClient().getDiscount();
        if (discount > 0) amount -= amount * discount / 100;

        return amount;
    }


    public long createOrder(Order order) {
        double amount = getTotalAmount(order);

        order.setAmount(amount);
        order.setTax(amount * 0.06);
        order.setTotalAmount(amount + (amount * 0.06));

        return orderDb.createOrder(order);
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
