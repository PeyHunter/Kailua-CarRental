package Models;

public class Address
{
    private int     addressID;
    private String  street;
    private String  zipCode;
    private String  city;


    // Constructor for new address (without ID)
    public Address(String street, String zipCode, String city) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }


    //Construktor for existing Address (with ID)
    public Address(int addressID, String street, String zipCode, String city)
    {
        this.addressID = addressID;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    //getters

    public int          getAddress()    {return addressID;}
    public String       getStreet()     {return street;}
    public String       getZipCode()    {return zipCode;}
    public String       getCity()       {return city;}

    //setters

    public void       setAddress(int addressID)       {this.addressID = addressID;}
    public void       setStreet(String street)      {this.street = street;}
    public void       setZipCode(String zipCode)    {this.zipCode = zipCode;}
    public void       setCity(String city)          {this.city = city;}


    @Override
    public String toString()
    {
        return "Address: " + addressID + "\n" +
                "Street: " + street + "\n" +
                "Zip Code: " + zipCode + "\n" +
                "City: " + city + "\n";
    }


}



    //CREATE TABLE Address (
    //        address_id INT AUTO_INCREMENT PRIMARY KEY,
    //        street VARCHAR(255) NOT NULL,
    //zip_code VARCHAR(10) NOT NULL,
    //city VARCHAR(50) NOT NULL
    //);