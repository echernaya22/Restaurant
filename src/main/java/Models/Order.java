package Models;

import java.util.Date;
import java.util.List;

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
}
