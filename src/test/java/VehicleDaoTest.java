import java.util.Date;

import dao.VehicleDao;
import dao.WarehouseDao;
import model.Vehicle;
import model.Warehouse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class VehicleDaoTest {

    private VehicleDao vehicleDao;

    @Before
    public void before() {
        vehicleDao = new VehicleDao();
    }

    @After
    public void after() {
        vehicleDao = null;
    }

    @Test
    public void saveVehicleTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("model.Vehicle", 2, warehouse, new Date());
        WarehouseDao warehouseDao = new WarehouseDao();
        warehouseDao.save(warehouse);
        vehicleDao.save(vehicle);

        Vehicle check = vehicleDao.get(vehicle.getId());
        Assert.assertEquals(vehicle.getId(), check.getId());
    }

    @Test
    public void getVehicleTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle1 = new Vehicle("model.Vehicle", 2, warehouse, new Date());
        Vehicle vehicle2 = new Vehicle("model.Vehicle", 2, warehouse, new Date());
        WarehouseDao warehouseDao = new WarehouseDao();
        warehouseDao.save(warehouse);
        vehicleDao.save(vehicle1);
        vehicleDao.save(vehicle2);

        Vehicle check1 = vehicleDao.get(vehicle1.getId());
        Vehicle check2 = vehicleDao.get(vehicle2.getId());

        Assert.assertEquals(check1.getId(), vehicle1.getId());
        Assert.assertEquals(check2.getId(), vehicle2.getId());

        Assert.assertNotEquals(check1.getId(), vehicle2.getId());
        Assert.assertNotEquals(check2.getId(), vehicle1.getId());
    }

    @Test
    public void removeNonExistingVehicleTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("model.Vehicle", 2, warehouse, new Date());
        WarehouseDao warehouseDao = new WarehouseDao();
        warehouseDao.save(warehouse);
        vehicleDao.save(vehicle);

        boolean removed = vehicleDao.remove(-1);

        Assert.assertFalse(removed);
    }

    @Test
    public void removeExistingVehicleTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("model.Vehicle", 2, warehouse, new Date());
        WarehouseDao warehouseDao = new WarehouseDao();
        warehouseDao.save(warehouse);
        vehicleDao.save(vehicle);

        boolean removed = vehicleDao.remove(vehicle.getId());

        Assert.assertTrue(removed);
    }

}