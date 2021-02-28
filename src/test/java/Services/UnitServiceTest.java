package Services;

import Models.Unit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UnitServiceTest {
    private Connection connection;
    private UnitService unitService = new UnitService();

    public UnitServiceTest() {

    }
    @BeforeClass
    public void before() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Restaurant;user=admin;password=12345";
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUnitById() throws SQLException {
        Unit unit = new Unit();
        unit.setUnitId(1);
        unit.setName("gram");
        Unit expected = unitService.getById(1);

        Assert.assertEquals(unit, expected);
    }

    @Test
    public void getAllUnits() throws SQLException {
        Unit unit = new Unit();
        unit.setUnitId(1);
        unit.setName("gram");
        Unit unit1 = new Unit();
        unit1.setUnitId(2);
        unit1.setName("milliliter");
        Unit unit2 = new Unit();
        unit2.setUnitId(3);
        unit2.setName("piece");

        List<Unit> unitList = new LinkedList<>();
        unitList.add(unit);
        unitList.add(unit1);
        unitList.add(unit2);

        List<Unit> unitListFromDb = unitService.getAll();

        Assert.assertEquals(unitList, unitListFromDb);
    }

    @Test
    public void createUnit() throws SQLException {
        Unit unit = new Unit("testUnit");
        long expectedKey = unitService.create(unit);
        unit.setUnitId(expectedKey);

        Assert.assertEquals(unit, unitService.getById(expectedKey));
        unitService.delete(expectedKey);
    }

    @Test
    public void deleteUnit() throws SQLException {
        Unit unit = new Unit("testUnit");
        long expectedKey = unitService.create(unit);
        unit.setUnitId(expectedKey);
        unitService.delete(expectedKey);
        Unit deletedUnit = unitService.getById(expectedKey);
        Assert.assertEquals(deletedUnit.getUnitId(), 0);
    }

    @Test
    public void updateUnit() throws SQLException {
        Unit unit = new Unit("testUnit");
        long expectedKey = unitService.create(unit);
        Unit newUnitToUpdate = new Unit("new name");
        newUnitToUpdate.setUnitId(expectedKey);

        unitService.update(newUnitToUpdate, expectedKey);

        Assert.assertEquals(newUnitToUpdate, unitService.getById(expectedKey));

        unitService.delete(expectedKey);
    }
}
