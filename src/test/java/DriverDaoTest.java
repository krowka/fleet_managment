import java.util.Date;

import dao.DriverDao;
import dao.WarehouseDao;
import model.Driver;
import model.Warehouse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DriverDaoTest {

    private DriverDao driverDao;

    @Before
    public void before() {
        driverDao = new DriverDao();
    }

    @After
    public void after() {
        driverDao = null;
    }


    @Test
    public void saveDriverTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Driver driver = new Driver("name", "surname", warehouse, new Date());
        WarehouseDao warehouseDao = new WarehouseDao();
        warehouseDao.save(warehouse);
        driverDao.save(driver);

        Driver check = driverDao.get(driver.getId());
        Assert.assertEquals(driver.getId(), check.getId());
    }


    @Test
    public void getDriverTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Driver driver1 = new Driver("name", "surname", warehouse, new Date());
        Driver driver2 = new Driver("name", "surname", warehouse, new Date());
        WarehouseDao warehouseDao = new WarehouseDao();
        warehouseDao.save(warehouse);
        driverDao.save(driver1);
        driverDao.save(driver2);

        Driver check1 = driverDao.get(driver1.getId());
        Driver check2 = driverDao.get(driver2.getId());

        Assert.assertEquals(check1.getId(), driver1.getId());
        Assert.assertEquals(check2.getId(), driver2.getId());

        Assert.assertNotEquals(check1.getId(), driver2.getId());
        Assert.assertNotEquals(check2.getId(), driver1.getId());
    }


    @Test
    public void removeNonExistingDriverTest() {
        boolean removed = driverDao.remove(-1);

        Assert.assertFalse(removed);
    }


    @Test
    public void removeExistingDriverTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Driver driver = new Driver("name", "surname", warehouse, new Date());
        WarehouseDao warehouseDao = new WarehouseDao();
        warehouseDao.save(warehouse);
        driverDao.save(driver);

        boolean removed = driverDao.remove(driver.getId());

        Assert.assertTrue(removed);
    }
}