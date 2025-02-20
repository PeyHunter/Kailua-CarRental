package App;

import java.sql.Date;


import DAO.*;
import Models.Address;
import Models.Car;
import Database.DataBaseConnection;
import Database.DataBaseQuery;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import Models.Person;
import Models.RentalContract;


import java.sql.Connection;


public class Main
{
    public static void main(String[] args)
    {

        //CONNECTION TO DATABASE
        Connection connection = DataBaseConnection.getConnection();
        if (connection == null)
        {
            System.out.println("Database connection failed.");
            return;
        }
        System.out.println("Database connection successful!");

        //Instanser af DAO klasserne
        PersonDAO personDAO = new PersonDAO                         (connection);
        AddressDAO addressDAO = new AddressDAO                      (connection);
        CarDAO carDAO = new CarDAO                                  (connection);
        CarGroupDAO carGroupDAO = new CarGroupDAO                   (connection);
        RentalContractDAO rentalContractDAO = new RentalContractDAO (connection);

        Scanner scanner = new Scanner(System.in);


        while (true)
        {
            System.out.println("\nCar Rental System - Choose an option:");
            System.out.println("1. Add Person");
            System.out.println("2. Update Person");
            System.out.println("3. Delete Person");
            System.out.println("4. View All Persons");
            System.out.println("5. Update Address");
            System.out.println("6. See all Cars");
            System.out.println("7. Add Rental Contract");
            System.out.println("8. Delete Rental Contract");
            System.out.println("9. View All Rental Contracts");
            System.out.println("10. Find Person, Car og RentalContract by ID");
            System.out.println("11. Exit");

            int choice;
            if (scanner.hasNextInt())
            {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else
            {
                System.out.println("\"Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            
            switch (choice)
            {
                case 1 -> //Add Person
                {
                    // Prompt for address details
                    System.out.println("Add address of the person first. Enter street: ");
                    String street = scanner.nextLine();
                    System.out.println("Enter city: ");
                    String city = scanner.nextLine();
                    System.out.println("Enter postal code: ");
                    String zipCode = scanner.nextLine();

                    // Create and insert the address
                    Address address = new Address(street, zipCode, city);
                    addressDAO.insert(address); // Insert the address
                    int addressID = address.getAddressID(); // Retrieve the generated ID

                    // Now, add the person
                    System.out.println("Now enter person details. Enter full name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter mobile phone:");
                    String phone = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter driver license:");
                    String license = scanner.nextLine();
                    System.out.println("Enter driver since (YYYY-MM-DD):");
                    String driverSince = scanner.nextLine();


                    Person person = new Person(name, phone, email, license, Date.valueOf(driverSince), addressID);
                    personDAO.insert(person);

                    int personID = -1; // Default
                    try (Statement stmt = connection.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()"))
                    {
                        if (rs.next())
                        {
                            personID = rs.getInt(1); // Get the last inserted ID
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    Person addedPerson = personDAO.selectById(personID);
                    if (addedPerson != null)
                    {
                        System.out.println("\n" + "Person added!");
                        System.out.println("Details:");
                        System.out.println("Person ID: " + addedPerson.getPersonID()); // Ensure your Person class has this method
                        System.out.println("Full Name: " + addedPerson.getFullName());
                        System.out.println("Phone: " + addedPerson.getMobilePhone());
                        System.out.println("Email: " + addedPerson.getEmail());
                        System.out.println("Driver License: " + addedPerson.getDriverLicense());
                        System.out.println("Driver Since: " + addedPerson.getDriverSince());
                        System.out.println("Address ID: " + addedPerson.getAddressID());
                    } else
                    {
                        System.out.println("Error retrieving person details.");
                    }
                }
                case 2 -> //Update Person
                {
                    //update person

                    //find person by id
                    System.out.println("Enter the Person ID to update:");
                    int personID = scanner.nextInt();
                    scanner.nextLine();

                    // return person by id
                    Person personToUpdate = personDAO.selectById(personID);
                    if (personToUpdate == null)
                    {
                        System.out.println("No person found with ID: " + personID);
                        break;
                    }

                    System.out.println("Current Details:");
                    System.out.println(personToUpdate);

                    System.out.println("Enter new full name (leave blank to keep current):");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty())
                    {
                        personToUpdate.setFullName(newName);
                    }

                    System.out.println("Enter new mobile phone (leave blank to keep current):");
                    String newPhone = scanner.nextLine();
                    if (!newPhone.isEmpty())
                    {
                        personToUpdate.setMobilePhone(newPhone);
                    }

                    System.out.println("Enter new email (leave blank to keep current):");
                    String newEmail = scanner.nextLine();
                    if (!newEmail.isEmpty())
                    {
                        personToUpdate.setEmail(newEmail);
                    }

                    System.out.println("Enter new driver license (leave blank to keep current):");
                    String newLicense = scanner.nextLine();
                    if (!newLicense.isEmpty())
                    {
                        personToUpdate.setDriverLicense(newLicense);
                    }

                    System.out.println("Enter new driver since (YYYY-MM-DD, leave blank to keep current):");
                    String newDriverSince = scanner.nextLine();
                    if (!newDriverSince.isEmpty())
                    {
                        personToUpdate.setDriverSince(Date.valueOf(newDriverSince));
                    }

                    System.out.println("Enter new address ID (leave blank to keep current):");
                    String newAddressID = scanner.nextLine();
                    if (!newAddressID.isEmpty())
                    {
                        personToUpdate.setAddressID(Integer.parseInt(newAddressID));
                    }

                    // Update the person in the database
                    personDAO.update(personToUpdate);

                    if (personToUpdate != null)
                    {
                        System.out.println("\n" + "Person added!");
                        System.out.println("Details:");
                        System.out.println("Person ID: " + personToUpdate.getPersonID()); // Ensure your Person class has this method
                        System.out.println("Full Name: " + personToUpdate.getFullName());
                        System.out.println("Phone: " + personToUpdate.getMobilePhone());
                        System.out.println("Email: " + personToUpdate.getEmail());
                        System.out.println("Driver License: " + personToUpdate.getDriverLicense());
                        System.out.println("Driver Since: " + personToUpdate.getDriverSince());
                        System.out.println("Address ID: " + personToUpdate.getAddressID());
                    } else
                    {
                        System.out.println("Error retrieving person details.");
                    }

                }
                case 3 -> //Delete Person
                {
                    // delete person
                    System.out.println("Enter the Person ID to delete:");
                    int personIDToDelete = scanner.nextInt();
                    scanner.nextLine();

                    // Check if the person exists
                    Person personToDelete = personDAO.selectById(personIDToDelete);
                    if (personToDelete == null)
                    {
                        System.out.println("No person found with ID: " + personIDToDelete);
                    } else
                    {
                        personDAO.delete(personIDToDelete);
                        System.out.println("Person with ID " + personIDToDelete + " has been deleted successfully.");
                    }


                    if (personToDelete != null)
                    {
                        System.out.println("\n" + "Person added!");
                        System.out.println("Details:");
                        System.out.println("Person ID: " + personToDelete.getPersonID()); // Ensure your Person class has this method
                        System.out.println("Full Name: " + personToDelete.getFullName());
                        System.out.println("Phone: " + personToDelete.getMobilePhone());
                        System.out.println("Email: " + personToDelete.getEmail());
                        System.out.println("Driver License: " + personToDelete.getDriverLicense());
                        System.out.println("Driver Since: " + personToDelete.getDriverSince());
                        System.out.println("Address ID: " + personToDelete.getAddressID());
                    } else
                    {
                        System.out.println("Error retrieving person details.");
                    }

                }
                case 4 -> //View All Persons
                {
                    List<Person> persons = personDAO.selectAll(); // Ensure you have this method in PersonDAO
                    System.out.println("List of Persons:");
                    for (Person p : persons)
                    {
                        System.out.println(p); // Ensure your Person class has a meaningful toString method
                    }
                }
                case 5 -> //Update Address
                {
                    System.out.println("Enter the Address ID to update:");
                    int addressID = scanner.nextInt();
                    scanner.nextLine();

                    Address addressToUpdate = addressDAO.selectById(addressID);
                    if (addressToUpdate == null)
                    {
                        System.out.println("No address found with ID: " + addressID);
                        break;
                    }

                    System.out.println("Current Details:");
                    System.out.println(addressToUpdate);

                    System.out.println("Enter new street (leave blank to keep current):");
                    String newStreet = scanner.nextLine();
                    if (!newStreet.isEmpty())
                    {
                        addressToUpdate.setStreet(newStreet);
                    }

                    System.out.println("Enter new city (leave blank to keep current):");
                    String newCity = scanner.nextLine();
                    if (!newCity.isEmpty())
                    {
                        addressToUpdate.setCity(newCity);
                    }

                    System.out.println("Enter new postal code (leave blank to keep current):");
                    String newZipCode = scanner.nextLine();
                    if (!newZipCode.isEmpty())
                    {
                        addressToUpdate.setZipCode(newZipCode);
                    }

                    addressDAO.update(addressToUpdate);

                    System.out.println("Address updated successfully!");
                    System.out.println("Updated Details:");
                    System.out.println(addressToUpdate);

                }
                case 6 -> //See all Cars
                {
                    //view cars

                    List<Car> cars = carDAO.selectAll();
                    System.out.println("List of Cars");
                    for (Car car : cars)
                    {
                        System.out.println(car);
                    }


                }
                case 7 -> //Add Rental Contract
                {
                    // Add rental contract

                    System.out.println("Enter Person ID:");
                    int personID = scanner.nextInt();
                    scanner.nextLine();

                    Person person = personDAO.selectById(personID);
                    if (person == null)
                    {
                        System.out.println("No person found with ID: " + personID);
                        break;
                    }

                    System.out.println("Enter Car ID:");
                    int carID = scanner.nextInt();
                    scanner.nextLine();

                    Car car = carDAO.selectById(carID);
                    if (car == null)
                    {
                        System.out.println("No car found with ID: " + carID);
                        break;
                    }


                    System.out.println("Enter rental start date (YYYY-MM-DD):");
                    String fromDateInput = scanner.nextLine();
                    Date fromDate = Date.valueOf(fromDateInput);

                    System.out.println("Enter rental end date (YYYY-MM-DD):");
                    String toDateInput = scanner.nextLine();
                    Date toDate = Date.valueOf(toDateInput);

                    System.out.println("Enter maximum kilometers allowed:");
                    int maxKm = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter starting odometer reading:");
                    int startOdometer = scanner.nextInt();
                    scanner.nextLine();

                    // Create a object of rentalcontract
                    RentalContract rentalContract = new RentalContract();
                    rentalContract.setPersonID(personID);
                    rentalContract.setCarID(carID);
                    rentalContract.setFromDatetime(fromDate);
                    rentalContract.setToDatetime(toDate);
                    rentalContract.setMaxKm(maxKm);
                    rentalContract.setStartOdometer(startOdometer);

                    // Insert the rentalcontract
                    rentalContractDAO.insert(rentalContract);
                    System.out.println("Rental contract added successfully!");

                    if (rentalContract != null)
                    {
                        System.out.println("\n" + "Rental Contract added!");
                        System.out.println("Details:");
                        System.out.println("Rental ID: " + rentalContract.getRentalID());
                        System.out.println("Person ID: " + rentalContract.getPersonID());
                        System.out.println("Car ID: " + rentalContract.getCarID());
                        System.out.println("From Date: " + rentalContract.getFromDatetime());
                        System.out.println("To Date: " + rentalContract.getToDatetime());
                        System.out.println("Max KM: " + rentalContract.getMaxKm());
                        System.out.println("Start Odometer: " + rentalContract.getStartOdometer());
                    } else
                    {
                        System.out.println("Error retrieving rental contract details.");
                    }

                }
                case 8 -> //Delete Rental Contract
                {
                    // delete contract
                    System.out.println("Enter the Rental ID to delete:");
                    int rentalIDToDelete = scanner.nextInt();
                    scanner.nextLine();

                    RentalContract rentalContractToDelete = rentalContractDAO.selectById(rentalIDToDelete);
                    if (rentalContractToDelete == null)
                    {
                        System.out.println("No rental contract found with ID: " + rentalIDToDelete);
                    } else
                    {
                        rentalContractDAO.delete(rentalIDToDelete);
                        System.out.println("Rental contract with ID " + rentalIDToDelete + " has been deleted successfully.");
                    }

                }
                case 9 -> //View All Rental Contracts
                {
                    List<RentalContract> rentalContracts = rentalContractDAO.selectAll();
                    System.out.println("List of Cars");
                    for (RentalContract rentalContract : rentalContracts)
                    {
                        System.out.println(rentalContract);
                    }


                }
                case 10 -> //Find Person, Car og RentalContract by ID
                {
                    boolean finding = true;
                    while (finding) {
                        System.out.println("Select type to find by ID:");
                        System.out.println("1. Find Person");
                        System.out.println("2. Find Car");
                        System.out.println("3. Find RentalContract");
                        System.out.println("0. Exit to Main menu");

                        int choiceID = scanner.nextInt();
                        scanner.nextLine(); // Clear the buffer after nextInt()
                        switch (choiceID) {
                            case 1 -> {
                                System.out.println("Enter Person ID");
                                int personID = scanner.nextInt();
                                scanner.nextLine(); // Clear the buffer again

                                Person person = personDAO.selectById(personID);
                                if (person != null) {
                                    System.out.println("Person found: " + person);
                                } else {
                                    System.out.println("No person found with ID: " + personID);
                                }
                            }
                            case 2 -> {
                                System.out.println("Enter Car ID:");
                                int carId = scanner.nextInt();
                                scanner.nextLine(); // Clear the buffer again

                                Car car = carDAO.selectById(carId);
                                if (car != null) {
                                    System.out.println("Car found: " + car);
                                } else {
                                    System.out.println("No car found with ID: " + carId);
                                }
                            }
                            case 3 -> {
                                System.out.println("Enter RentalContract ID:");
                                int rentalContractId = scanner.nextInt();
                                scanner.nextLine(); // Clear the buffer again

                                RentalContract rentalContract = rentalContractDAO.selectById(rentalContractId);
                                if (rentalContract != null) {
                                    System.out.println("RentalContract found: " + rentalContract);
                                } else {
                                    System.out.println("No RentalContract found with ID: " + rentalContractId);
                                }
                            }
                            case 0 -> finding = false; // Exit the loop
                            default -> System.out.println("Invalid choice. Please select 1, 2, or 3.");
                        }
                    }
                }
                case 11 -> //Exit
                {
                    //exit
                    System.out.println("Exiting...");
                    return; // Exit the program

                }
                default -> System.out.println("Invalid choice! Try again.");

            }
        }
    }
}