package Models;

public class Unit {
    private long unitId;
    private String name;

    public Unit() {

    }

    public Unit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }
}
