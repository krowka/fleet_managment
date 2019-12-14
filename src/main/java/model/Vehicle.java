package model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVehicle", unique = true, nullable = false)
    private int id;

    @Column(name = "Type", nullable = false)
    private String type;

    @Column(name = "DriverSeats", nullable = false)
    private int driverSeats;

    @ManyToOne
    @JoinColumn(name = "StartLocalization", nullable = false)
    private Warehouse startLocalization;

    @Column(name = "BuyDate", nullable = false)
    private Date buyDate;

    @Column(name = "SellDate", nullable = true)
    private Date sellDate;

    public Vehicle() {
    }

    public String getType () {
        return this.type;
    }

    public int getId () {
        return this.id;
    }

    public Vehicle(String type, int driverSeats, Warehouse startLocalization, Date buyDate) {
        this.type = type;
        this.driverSeats = driverSeats;
        this.startLocalization = startLocalization;
        this.buyDate = buyDate;
    }

    public void setSellDate(Date sellDate) {
        if (this.buyDate.compareTo(sellDate) < 0)
            this.sellDate = sellDate;
    }

    public Warehouse getCurrentLocalization() {
        return null;
    }
}
