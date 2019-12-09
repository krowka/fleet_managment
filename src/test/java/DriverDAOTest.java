import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DriverDAOTest {

    private DriverDAO driverDAO;

    @Before
    public void before() {
        driverDAO = new DriverDAO();
    }

    @After
    public void after() {
        driverDAO = null;
    }


    @Test
    public void saveDriverTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Driver driver = new Driver("name", "surname", warehouse, new Date());
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.save(warehouse);
        driverDAO.save(driver);

        Driver check = driverDAO.get(driver.getId());
        Assert.assertEquals(driver.getId(), check.getId());
    }


    @Test
    public void getDriverTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Driver driver1 = new Driver("name", "surname", warehouse, new Date());
        Driver driver2 = new Driver("name", "surname", warehouse, new Date());
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.save(warehouse);
        driverDAO.save(driver1);
        driverDAO.save(driver2);

        Driver check1 = driverDAO.get(driver1.getId());
        Driver check2 = driverDAO.get(driver2.getId());

        Assert.assertEquals(check1.getId(), driver1.getId());
        Assert.assertEquals(check2.getId(), driver2.getId());

        Assert.assertNotEquals(check1.getId(), driver2.getId());
        Assert.assertNotEquals(check2.getId(), driver1.getId());
    }


    @Test
    public void removeNonExistingDriverTest() {
        boolean removed = driverDAO.remove(-1);

        Assert.assertFalse(removed);
    }


    @Test
    public void removeExistingDriverTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Driver driver = new Driver("name", "surname", warehouse, new Date());
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.save(warehouse);
        driverDAO.save(driver);

        boolean removed = driverDAO.remove(driver.getId());

        Assert.assertTrue(removed);
    }
}