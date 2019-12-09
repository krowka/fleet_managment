import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Departures")
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDeparture", unique = true, nullable = false)
    private int id;

    @Column(name = "StartDate", nullable = false)
    private Date startDate;

    @Column(name = "EndDate", nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "DepartureFrom", nullable = false)
    private Warehouse departureFrom;

    @ManyToOne
    @JoinColumn(name = "DepartureTo", nullable = false)
    private Warehouse departureTo;

    @ManyToOne
    @JoinColumn(name = "IdVehicle", nullable = false)
    private Vehicle vehicle;

    @Column(name = "CreateDate", nullable = false)
    private Date createDate;

    @ManyToMany()
    @JoinTable(
            name = "DeparturesDrivers",
            joinColumns = {@JoinColumn(name = "IdDeparture")},
            inverseJoinColumns = {@JoinColumn(name = "IdDriver")}
    )
    private List<Driver> drivers;

    public Departure() {
    }

    public int getId () {
        return this.id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Departure(Date startDate, Date endDate, Warehouse departureFrom, Warehouse departureTo, Vehicle vehicle, Date createDate, List<Driver> drivers) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.departureFrom = departureFrom;
        this.departureTo = departureTo;
        this.vehicle = vehicle;
        this.createDate = createDate;
        this.drivers = drivers;
    }
}
