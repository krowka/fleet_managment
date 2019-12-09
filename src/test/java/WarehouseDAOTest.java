import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class WarehouseDAOTest {

    private WarehouseDAO warehouseDAO;

    @Before
    public void before() {
        warehouseDAO = new WarehouseDAO();
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

    @Test(expected = ObjectNotFoundException.class)
    public void getWarehouseExceptionTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        warehouseDAO.save(warehouse);

        warehouseDAO.get(warehouse.getId() + 10).getName();
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
        Warehouse warehouse = new Warehouse("Warehouse", new Date());
        warehouseDAO.save(warehouse);

        boolean removed = warehouseDAO.remove(warehouse.getId() + 1);

        Assert.assertFalse(removed);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void removeExistingWarehouseTest() {
        Warehouse warehouse = new Warehouse("Warehouse", new Date());
        warehouseDAO.save(warehouse);
        boolean removed = warehouseDAO.remove(warehouse.getId());

        Assert.assertTrue(removed);
        warehouseDAO.get(warehouse.getId()).getName();
    }
}