package DAO;

import Models.Car;
import Models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CarDAO implements GenericDAO<Car>
{
    private Connection connection;

    public CarDAO(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void insert(Car car) {
        String sql = "INSERT INTO Car (brand, model, fuel_type, registration_number, first_registration, odometer, car_group_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, car.getBrand());
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getFuelType());
            pstmt.setString(4, car.getRegistrationNumber());
            pstmt.setDate(5, new java.sql.Date(car.getFirstRegistration().getTime())); // Use java.sql.Date
            pstmt.setInt(6, car.getOdometer());
            pstmt.setInt(7, car.getCarGroupID());
            pstmt.executeUpdate();

            // Retrieve the generated carID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                car.setCarID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Car car) {
        String sql = "UPDATE Car SET brand = ?, model = ?, fuel_type = ?, registration_number = ?, first_registration = ?, odometer = ?, car_group_id = ? WHERE car_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, car.getBrand());
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getFuelType());
            pstmt.setString(4, car.getRegistrationNumber());
            pstmt.setDate(5, new java.sql.Date(car.getFirstRegistration().getTime())); // Use java.sql.Date
            pstmt.setInt(6, car.getOdometer());
            pstmt.setInt(7, car.getCarGroupID());
            pstmt.setInt(8, car.getCarID());
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Car updated successfully.");
            } else {
                System.out.println("No car found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Car WHERE car_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id); // Use the id parameter instead of car.getCarID()
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Car deleted successfully.");
            } else {
                System.out.println("No car found with that ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car selectById(int id) {
        String sql = "SELECT * FROM Car WHERE car_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Car(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("fuel_type"),
                        rs.getString("registration_number"),
                        rs.getDate("first_registration"),
                        rs.getInt("odometer"),
                        rs.getInt("car_group_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> selectAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM Car"; // Use the correct table name

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("fuel_type"),
                        rs.getString("registration_number"),
                        rs.getDate("first_registration"),
                        rs.getInt("odometer"),
                        rs.getInt("car_group_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
}
