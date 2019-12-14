package dao;

import model.Vehicle;

public class VehicleDao extends GenericDao<Vehicle> {
    public VehicleDao() {
        super(Vehicle.class);
    }
}