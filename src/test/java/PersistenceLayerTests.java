import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersistenceLayerTests {

    private final WarehouseDAO warehouseDAO = new WarehouseDAO();

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void createStudentTest() {
        Warehouse warehouse = new Warehouse("first", new Date());
        warehouseDAO.save(warehouse);

        Warehouse check = warehouseDAO.get(warehouse.getId());
        Assert.assertEquals(warehouse, check);
    }
}