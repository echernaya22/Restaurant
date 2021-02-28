package Models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return unitId == unit.unitId &&
                Objects.equals(name, unit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitId, name);
    }
}
