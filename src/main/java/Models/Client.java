package Models;

import java.util.Objects;

public class Client {
    private long id;
    private String surname;
    private String name;
    private String phoneNumber;
    private double discount;
    

    public Client() {

    }

    public Client (String surname, String name, String phoneNumber, double discount) {
        this.surname = surname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.discount = discount;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", sirname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Double.compare(client.discount, discount) == 0 &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(name, client.name) &&
                phoneNumber.equals(client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, phoneNumber, discount);
    }
}
