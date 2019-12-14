import dao.WarehouseDao;
import model.Warehouse;

import java.util.Date;

public class Main {


    public static void main(String[] args) {
        WarehouseDao warehouseDao = new WarehouseDao();
        Warehouse testWarehouse = new Warehouse("WYZIMA PÓŁNOCNA", new Date());

        warehouseDao.save(testWarehouse);

        Warehouse gotWarehouse = warehouseDao.get(testWarehouse.getId());
        System.out.println(gotWarehouse.getName());

        warehouseDao.remove(testWarehouse.getId());
    }

}