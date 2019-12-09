import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class VehicleDAOTest {

    private VehicleDAO vehicleDAO;

    @Before
    public void before() {
        vehicleDAO = new VehicleDAO();
    }

    @After
    public void after() {
        vehicleDAO = null;
    }



    @Test
    public void saveVehicleTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("Vehicle", 2, warehouse, new Date());
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.save(warehouse);
        vehicleDAO.save(vehicle);

        Vehicle check = vehicleDAO.get(vehicle.getId());
        Assert.assertEquals(vehicle.getId(), check.getId());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void getVehicleExceptionTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("Vehicle", 2, warehouse, new Date());
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.save(warehouse);
        vehicleDAO.save(vehicle);

        vehicleDAO.get(vehicle.getId() + 10).getCurrentLocalization();
    }

    @Test
    public void getVehicleTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle1 = new Vehicle("Vehicle", 2, warehouse, new Date());
        Vehicle vehicle2 = new Vehicle("Vehicle", 2, warehouse, new Date());
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.save(warehouse);
        vehicleDAO.save(vehicle1);
        vehicleDAO.save(vehicle2);

        Vehicle check1 = vehicleDAO.get(vehicle1.getId());
        Vehicle check2 = vehicleDAO.get(vehicle2.getId());

        Assert.assertEquals(check1.getId(), vehicle1.getId());
        Assert.assertEquals(check2.getId(), vehicle2.getId());

        Assert.assertNotEquals(check1.getId(), vehicle2.getId());
        Assert.assertNotEquals(check2.getId(), vehicle1.getId());
    }

    @Test
    public void removeNonExistingWarehouseTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("Vehicle", 2, warehouse, new Date());
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.save(warehouse);
        vehicleDAO.save(vehicle);

        boolean removed = vehicleDAO.remove(vehicle.getId() + 10);

        Assert.assertFalse(removed);
    }

    @Test
    public void removeExistingWarehouseTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("Vehicle", 2, warehouse, new Date());
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.save(warehouse);
        vehicleDAO.save(vehicle);

        boolean removed = vehicleDAO.remove(vehicle.getId());

        Assert.assertTrue(removed);
    }

}