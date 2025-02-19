package Models;
import java.util.Date;

public class Car
{

    private int     carID;
    private String  brand;
    private String  model;
    private String  fuelType;
    private String  registrationNumber;
    private Date    firstRegistration;
    private int     odometer;
    private int     carGroupID;

    //Constructor for new car(without ID)
    public Car(String brand, String model, String fuelType, String registrationNumber, Date firstRegistration, int odometer, int carGroupID)
    {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this. registrationNumber = registrationNumber;
        this.firstRegistration = firstRegistration;
        this.odometer = odometer;
        this.carGroupID = carGroupID;
    }

    //
    public Car(int carID,String brand, String model, String fuelType, String registrationNumber, Date firstRegistration, int odometer, int carGroupID)
    {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this. registrationNumber = registrationNumber;
        this.firstRegistration = firstRegistration;
        this.odometer = odometer;
        this.carGroupID = carGroupID;
    }

    // getters

    public int      getCarID()                 {return carID;}
    public String   getBrand()                 {return brand;}
    public String   getModel()                 {return model;}
    public String   getFuelType()              {return fuelType;}
    public String   getRegistrationNumber()    {return registrationNumber;}
    public Date     getFirstRegistration()     {return firstRegistration;}
    public int      getOdometer()              {return odometer;}
    public int      getCarGroupID()            {return carGroupID;}

    //setters

    public void     setCarID(int carID)        {this.carID = carID;}
    public void     getBrand()                 {return brand;}
    public void     getModel()                 {return model;}
    public void     getFuelType()              {return fuelType;}
    public void     getRegistrationNumber()    {return registrationNumber;}
    public void     getFirstRegistration()     {return firstRegistration;}
    public void     getOdometer()              {return odometer;}
    public void     getCarGroupID()            {return carGroupID;}











}


    //CREATE TABLE Car (
    //      car_id INT AUTO_INCREMENT PRIMARY KEY,
    //      brand VARCHAR(50) NOT NULL,
    //      model VARCHAR(50) NOT NULL,
    //      fuel_type VARCHAR(20) NOT NULL,
    //      registration_number VARCHAR(20) UNIQUE NOT NULL,
    //      first_registration DATE NOT NULL,
    //      odometer INT NOT NULL,
    //      car_group_id INT,
    //      FOREIGN KEY (car_group_id) REFERENCES CarGroup(car_group_id)
    //        );

