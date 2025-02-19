package Models;

import java.util.Date;

public class Person {

    private int         personID;
    private String      fullName;
    private String      mobilePhone;
    private String      email;
    private String      driverLicense;
    private Date        driverSince;
    private int         addressID;

    // Constructor for new person (without ID)
    public Person(String fullName, String mobilePhone, String email, String driverLicense, Date driverSince, int addressID) {
        this.fullName = fullName;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.driverLicense = driverLicense;
        this.driverSince = driverSince;
        this.addressID = addressID;
    }

    // Constructor with ID
    public Person(int personID, String fullName, String mobilePhone, String email,
                  String driverLicense, Date driverSince, int addressID)
    {
        this.personID = personID;
        this.fullName = fullName;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.driverLicense = driverLicense;
        this.driverSince = driverSince;
        this.addressID = addressID;
    }

    // Getters
    public int getPersonID()         { return personID; }
    public String getFullName()      { return fullName; }
    public String getMobilePhone()   { return mobilePhone; }
    public String getEmail()         { return email; }
    public String getDriverLicense() { return driverLicense; }
    public Date getDriverSince()     { return driverSince; }
    public int getAddressID()        { return addressID; }

    // Setters
    public void setPersonID(int personID)               { this.personID = personID; }
    public void setFullName(String fullName)            { this.fullName = fullName; }
    public void setMobilePhone(String mobilePhone)      { this.mobilePhone = mobilePhone; }
    public void setEmail(String email)                  { this.email = email; }
    public void setDriverLicense(String driverLicense)  { this.driverLicense = driverLicense; }
    public void setDriverSince(Date driverSince)        { this.driverSince = driverSince; }
    public void setAddressID(int addressID)             { this.addressID = addressID; }

    @Override
    public String toString() {
        return "Person ID: " + personID + "\n" +
                "Full Name: " + fullName + "\n" +
                "Mobile Phone: " + mobilePhone + "\n" +
                "Email: " + email + "\n" +
                "Driver License: " + driverLicense + "\n" +
                "Driver Since: " + driverSince + "\n" +
                "Address ID: " + addressID + "\n";
    }
}