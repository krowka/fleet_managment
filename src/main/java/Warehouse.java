import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return id == warehouse.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openDate=" + openDate +
                ", closeDate=" + closeDate +
                ", vehicles=" + vehicles +
                ", drivers=" + drivers +
                '}';
    }
}
