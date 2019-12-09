
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;


public class DepartureDAOTest {

    private DepartureDAO departureDAO;

    @Before
    public void before() {
        departureDAO = new DepartureDAO();
    }

    @After
    public void after() {
        departureDAO = null;
    }


    @Test
    public void saveDepartureTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("Vehicle", 2, warehouse, new Date());
        Departure departure = new Departure(new Date(), new Date(), warehouse, warehouse, vehicle, new Date(), null);
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        VehicleDAO vehicleDAO = new VehicleDAO();
        warehouseDAO.save(warehouse);
        vehicleDAO.save(vehicle);

        departureDAO.save(departure);

        Departure check = departureDAO.get(departure.getId());
        Assert.assertEquals(departure.getId(), check.getId());
    }


    @Test
    public void getDepartureTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("Vehicle", 2, warehouse, new Date());
        Departure departure1 = new Departure(new Date(), new Date(), warehouse, warehouse, vehicle, new Date(), null);
        Departure departure2 = new Departure(new Date(), new Date(), warehouse, warehouse, vehicle, new Date(), null);
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        VehicleDAO vehicleDAO = new VehicleDAO();
        warehouseDAO.save(warehouse);
        vehicleDAO.save(vehicle);

        departureDAO.save(departure1);
        departureDAO.save(departure2);


        Departure check1 = departureDAO.get(departure1.getId());
        Departure check2 = departureDAO.get(departure2.getId());

        Assert.assertEquals(check1.getId(), departure1.getId());
        Assert.assertEquals(check2.getId(), departure2.getId());

        Assert.assertNotEquals(check1.getId(), departure2.getId());
        Assert.assertNotEquals(check2.getId(), departure1.getId());
    }

    @Test
    public void removeNonExistingDepartureTest() {

        boolean removed = departureDAO.remove(-1);

        Assert.assertFalse(removed);
    }

    @Test
    public void removeExistingDepartureTest() {
        Warehouse warehouse = new Warehouse("test", new Date());
        Vehicle vehicle = new Vehicle("Vehicle", 2, warehouse, new Date());
        Departure departure = new Departure(new Date(), new Date(), warehouse, warehouse, vehicle, new Date(), null);
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        VehicleDAO vehicleDAO = new VehicleDAO();
        warehouseDAO.save(warehouse);
        vehicleDAO.save(vehicle);

        departureDAO.save(departure);

        boolean removed = departureDAO.remove(departure.getId());

        Assert.assertTrue(removed);
    }

}
