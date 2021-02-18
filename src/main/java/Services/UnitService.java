package Services;

import DAO.Implementations.UnitDAO;
import Models.Unit;

import java.sql.SQLException;
import java.util.List;

public class UnitService {
    private UnitDAO unitDb = new UnitDAO();

    public Unit getById(int id) throws SQLException {
        return unitDb.getById(id);
    }

    public List<Unit> getAll() throws SQLException {
        return unitDb.getAll();
    }
    public void create(Unit unit) throws SQLException {
        unitDb.create(unit);
    }
    public void update(Unit unit, int id) throws SQLException {
        unitDb.update(unit, id);
    }
    public void delete(int id) throws SQLException {
        unitDb.delete(id);
    }
}
