package Models;

import java.util.Objects;

public class OrderDetails {
    private Dish dish;
    private int quantity;

    public OrderDetails() {

    }

    public OrderDetails(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "dish=" + dish +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return quantity == that.quantity &&
                dish.toString().equals(that.dish.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dish, quantity);
    }
}
