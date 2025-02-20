package Models;

import java.util.Date;

public class RentalContract {

    private int rentalID;
    private int personID;
    private int carID;
    private Date fromDatetime;
    private Date toDatetime;
    private int maxKm;
    private int startOdometer;

    //default construkter
    public RentalContract() {}

    // Constructor for new rental contract (without ID)
    public RentalContract(int personID, int carID, Date fromDatetime, Date toDatetime, int maxKm, int startOdometer) {
        this.personID = personID;
        this.carID = carID;
        this.fromDatetime = fromDatetime;
        this.toDatetime = toDatetime;
        this.maxKm = maxKm;
        this.startOdometer = startOdometer;
    }

    // Constructor for existing rental contract (with ID)
    public RentalContract(int rentalID, int personID, int carID, Date fromDatetime, Date toDatetime, int maxKm, int startOdometer) {
        this.rentalID = rentalID;
        this.personID = personID;
        this.carID = carID;
        this.fromDatetime = fromDatetime;
        this.toDatetime = toDatetime;
        this.maxKm = maxKm;
        this.startOdometer = startOdometer;
    }

    // Getters

    public int getRentalID() {
        return rentalID;
    }

    public int getPersonID() {
        return personID;
    }

    public int getCarID() {
        return carID;
    }

    public Date getFromDatetime() {
        return fromDatetime;
    }

    public Date getToDatetime() {
        return toDatetime;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public int getStartOdometer() {
        return startOdometer;
    }

    // Setters
    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public void setFromDatetime(Date fromDatetime) {
        this.fromDatetime = fromDatetime;
    }

    public void setToDatetime(Date toDatetime) {
        this.toDatetime = toDatetime;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public void setStartOdometer(int startOdometer) {
        this.startOdometer = startOdometer;
    }

    @Override
    public String toString() {
        return "Rental ID: " + rentalID + "\n" +
                "Person ID: " + personID + "\n" +
                "Car ID: " + carID + "\n" +
                "From: " + fromDatetime + "\n" +
                "To: " + toDatetime + "\n" +
                "Max KM: " + maxKm + "\n" +
                "Start Odometer: " + startOdometer + "\n";
    }
}