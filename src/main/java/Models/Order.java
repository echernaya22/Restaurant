package Models;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private long id;
    private Client client;
    private Date date;
    private double amount;
    private double tax;
    private double totalAmount;
    private double tips;
    private List<OrderDetails> orderDetails;

    public Order() {

    }

    public Order(Client client, Date date, double amount, double tax, double totalAmount, double tips) {
        this.client = client;
        this.date = date;
        this.amount = amount;
        this.tax = tax;
        this.totalAmount = totalAmount;
        this.tips = tips;
    }

    public Order(Client client, Date date, List<OrderDetails> orderDetails) {
        this.client = client;
        this.date = date;
        this.orderDetails = orderDetails;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getTax() {
        return tax;
    }

    public double getTips() {
        return tips;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setTips(double tips) {
        this.tips = tips;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", date=" + date +
                ", amount=" + amount +
                ", tax=" + tax +
                ", totalAmount=" + totalAmount +
                ", tips=" + tips +
                ", orderDetails=" + orderDetails +
                '}' +
                '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.amount, amount) == 0 &&
                Double.compare(order.tax, tax) == 0 &&
                Double.compare(order.totalAmount, totalAmount) == 0 &&
                Double.compare(order.tips, tips) == 0 &&
                client.toString().equals(order.client.toString()) &&
                date.toString().equals(order.date.toString()) &&
                orderDetails.toString().equals(order.orderDetails.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, date, amount, tax, totalAmount, tips, orderDetails);
    }
}
