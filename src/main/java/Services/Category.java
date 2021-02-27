package Models;

public class Category {
    private int catergoryId;
    private String name;

    public Category() {

    }
    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCatergoryId() {
        return catergoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCatergoryId(int catergoryId) {
        this.catergoryId = catergoryId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catergoryId=" + catergoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
