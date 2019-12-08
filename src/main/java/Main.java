import java.util.Date;

public class Main {


    public static void main(String[] args) {
        WarehouseDAO persistenceLayer = new WarehouseDAO();
        Warehouse testWarehouse = new Warehouse("Magazyn A", new Date());

        persistenceLayer.save(testWarehouse);
    }

}