package Models;

import java.util.Objects;

public class Category {
    private long categoryId;
    private String name;

    public Category() {

    }
    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, name);
    }

}
