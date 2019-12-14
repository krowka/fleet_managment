import java.util.Date;

import dao.WarehouseDao;
import model.Warehouse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class WarehouseDaoTest {

    private WarehouseDao warehouseDAO;

    @Before
    public void before() {
        warehouseDAO = new WarehouseDao();
    }

    @After
    public void after() {
        warehouseDAO = null;
    }

    @Test
    public void saveWarehouseTest() {
        Warehouse warehouse = new Warehouse("newWarehouse", new Date());
        warehouseDAO.save(warehouse);

        Warehouse check = warehouseDAO.get(warehouse.getId());
        Assert.assertEquals(warehouse.getId(), check.getId());
    }

    @Test
    public void getWarehouseTest() {
        Warehouse warehouse1 = new Warehouse("Warehouse1", new Date());
        Warehouse warehouse2 = new Warehouse("Warehouse2", new Date());
        warehouseDAO.save(warehouse1);
        warehouseDAO.save(warehouse2);

        Warehouse check1 = warehouseDAO.get(warehouse1.getId());
        Warehouse check2 = warehouseDAO.get(warehouse2.getId());

        Assert.assertEquals(check1.getId(), warehouse1.getId());
        Assert.assertEquals(check2.getId(), warehouse2.getId());

        Assert.assertNotEquals(check1.getId(), warehouse2.getId());
        Assert.assertNotEquals(check2.getId(), warehouse1.getId());
    }

    @Test
    public void removeNonExistingWarehouseTest() {
        boolean removed = warehouseDAO.remove(-1);

        Assert.assertFalse(removed);
    }

    @Test
    public void removeExistingWarehouseTest() {
        Warehouse warehouse = new Warehouse("Warehouse", new Date());
        warehouseDAO.save(warehouse);
        boolean removed = warehouseDAO.remove(warehouse.getId());

        Assert.assertTrue(removed);
    }
}