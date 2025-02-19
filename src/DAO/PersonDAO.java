package DAO;

import Models.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonDAO implements GenericDAO<Person>
{
    private Connection connection;

    // construktor to initialize database connection
    public PersonDAO(Connection connection)
    {
        this.connection = connection;
    }


    @Override
    public void insert(Person person)
    {
        String sql = "INSERT INTO person (fullname, mobile_phone, email, driver_license, driver_since, address_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql))
        {
            pstmt.setString(1, person.getFullName());
            pstmt.setString(2, person.getMobilePhone());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getDriverLicense());
            pstmt.setDate(5, new java.sql.Date(person.getDriverSince().getTime()));
            pstmt.setInt(6, person.getAddressID());
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person person) {
        String sql = "UPDATE person SET fullname = ?, mobile_phone = ?, email = ?, driver_license = ?, driver_since = ?, address_id = ? WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, person.getFullName());
            pstmt.setString(2, person.getMobilePhone());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getDriverLicense());
            pstmt.setDate(5, new java.sql.Date(person.getDriverSince().getTime()));
            pstmt.setInt(6, person.getAddressID());
            pstmt.setString(7, person.getEmail()); // Using email to find the correct person
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Person updated successfully.");
            } else {
                System.out.println("No person found with that email.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM person WHERE address_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Person deleted successfully.");
            } else {
                System.out.println("No person found with that address ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person selectById(int id) {
        String sql = "SELECT * FROM person WHERE address_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Person(
                        rs.getString("fullname"),
                        rs.getString("mobile_phone"),
                        rs.getString("email"),
                        rs.getString("driver_license"),
                        rs.getDate("driver_since"),
                        rs.getInt("address_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Person> selectAll() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                people.add(new Person(
                        rs.getString("fullname"),
                        rs.getString("mobile_phone"),
                        rs.getString("email"),
                        rs.getString("driver_license"),
                        rs.getDate("driver_since"),
                        rs.getInt("address_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

//    public void update(Person person)
//    {
//        String sql = "UPDATE Person SET fullname = ?, mobile_phone = ?, email = ?, driver_license = ?, driver_since = ?, address_id = ? WHERE person_id = ?";
//        try(PreparedStatement pstmt = connection.prepareStatement(sql))
//        {
//            pstmt.setString(1, person.getFullName());
//            pstmt.setString(2, person.getMobilePhone());
//            pstmt.setString(3, person.getEmail());
//            pstmt.setString(4, person.getDriverLicense());
//            pstmt.setDate(5, new java.sql.Date(person.getDriverSince().getTime()));
//            pstmt.setInt(6, person.getAddressID());
//            pstmt.executeUpdate();
//        }
//            catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//    }






}
