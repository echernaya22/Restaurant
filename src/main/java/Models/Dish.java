package Models;

import java.util.Objects;

public class Dish {
    private long id;
    private String name;
    private Category category;
    private double price;
    private double weight;
    private Unit unit;

    public Dish() {

    }

    public Dish(String name, Category category, double price, double weight, Unit unit) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.weight = weight;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + category.getName() +
                ", price=" + price +
                ", weight=" + weight +
                ", unitId=" + unit.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id &&
                price == dish.price &&
                weight == dish.weight &&
                name.equals(dish.name) &&
                category.getName().equals(dish.category.getName()) &&
                unit.getName().equals(dish.unit.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price, weight, unit);
    }
}
