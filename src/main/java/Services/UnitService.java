package Services;

import DAO.Implementations.UnitDAO;
import Models.Unit;

import java.sql.SQLException;
import java.util.List;

public class UnitService {
    private UnitDAO unitDb = new UnitDAO();

    public Unit getById(long id) throws SQLException {
        return unitDb.getById(id);
    }

    public List<Unit> getAll() throws SQLException {
        return unitDb.getAll();
    }
    public long create(Unit unit) throws SQLException {
        return unitDb.create(unit);
    }
    public void update(Unit unit, long id) throws SQLException {
        unitDb.update(unit, id);
    }
    public void delete(long id) throws SQLException {
        unitDb.delete(id);
    }
}
