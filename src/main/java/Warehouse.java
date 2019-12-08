import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdWarehouse", unique = true, nullable = false)
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "OpenDate", nullable = false)
    private Date openDate;

    @Column(name = "CloseDate", nullable = true)
    private Date closeDate;

    @Transient
    private List<Vehicle> vehicles;

    @Transient
    private List<Driver> drivers;

    public Warehouse() {
    }

    public String getName () {
        return this.name;
    }

    public int getId () {
        return this.id;
    }

    public Warehouse(String name, Date openDate) {
        this.name = name;
        this.openDate = openDate;
    }

    public void setCloseDate(Date closeDate) {
        if(this.openDate.compareTo(closeDate) < 0)
            this.closeDate = closeDate;
    }
//    public List<Vehicle> vehiclesArriving(Date date) {
//        return null;
//    }
//
//    public List<Vehicle> vehiclesDeparting(Date date) {
//        return null;
//    }
//
//    public List<Vehicle> vehiclesAvailable(Date date) {
//        return null;
//    }
}
