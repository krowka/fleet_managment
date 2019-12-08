import javax.persistence.*;
import java.util.Date;

@Entity(name = "Drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDriver", unique = true, nullable = false)
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;

    @ManyToOne
    @JoinColumn(name = "StartLocalization", nullable = false)
    private Warehouse startLocalization;

    @Column(name = "HiredDate", nullable = false)
    private Date hiredDate;

    @Column(name = "FiredDate", nullable = true)
    private Date firedDate;

    public Driver() {
    }

    public String getName () {
        return this.name;
    }

    public int getId () {
        return this.id;
    }

    public Driver(String name, String surname, Warehouse startLocalization, Date hireDate) {
        this.name = name;
        this.surname = surname;
        this.startLocalization = startLocalization;
        this.hiredDate = hireDate;
    }

    public void setFiredDate(Date firedDate) {
        if (this.hiredDate.compareTo(firedDate) < 0)
            this.firedDate = firedDate;
    }

    public Warehouse getCurrentLocalization() {
        return null;
    }
}
