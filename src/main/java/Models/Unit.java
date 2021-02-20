package Models;

public class Unit {
    private int unitId;
    private String name;

    public Unit() {

    }

    public Unit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }
}
