package DAO;

import Models.RentalContract; // Import your RentalContract model
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalContractDAO implements GenericDAO<RentalContract> {
    private Connection connection;

    // Constructor to initialize the database connection
    public RentalContractDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(RentalContract rentalContract) {
        String sql = "INSERT INTO RentalContract (person_id, car_id, from_datetime, to_datetime, max_km, start_odometer) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, rentalContract.getPersonID());
            pstmt.setInt(2, rentalContract.getCarID());
            pstmt.setTimestamp(3, new Timestamp(rentalContract.getFromDatetime().getTime()));
            pstmt.setTimestamp(4, new Timestamp(rentalContract.getToDatetime().getTime()));
            pstmt.setInt(5, rentalContract.getMaxKm());
            pstmt.setInt(6, rentalContract.getStartOdometer());
            pstmt.executeUpdate();

            // Retrieve the generated rentalID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                rentalContract.setRentalID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(RentalContract rentalContract) {
        String sql = "UPDATE RentalContract SET person_id = ?, car_id = ?, from_datetime = ?, to_datetime = ?, max_km = ?, start_odometer = ? WHERE rental_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, rentalContract.getPersonID());
            pstmt.setInt(2, rentalContract.getCarID());
            pstmt.setTimestamp(3, new Timestamp(rentalContract.getFromDatetime().getTime()));
            pstmt.setTimestamp(4, new Timestamp(rentalContract.getToDatetime().getTime()));
            pstmt.setInt(5, rentalContract.getMaxKm());
            pstmt.setInt(6, rentalContract.getStartOdometer());
            pstmt.setInt(7, rentalContract.getRentalID()); // Use rentalID to find the correct contract

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Rental contract updated successfully.");
            } else {
                System.out.println("No rental contract found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM RentalContract WHERE rental_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Rental contract deleted successfully.");
            } else {
                System.out.println("No rental contract found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RentalContract selectById(int id) {
        String sql = "SELECT * FROM RentalContract WHERE rental_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new RentalContract(
                        rs.getInt("rental_id"), // Assuming RentalContract has a constructor that takes rentalID
                        rs.getInt("person_id"),
                        rs.getInt("car_id"),
                        rs.getTimestamp("from_datetime"),
                        rs.getTimestamp("to_datetime"),
                        rs.getInt("max_km"),
                        rs.getInt("start_odometer")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RentalContract> selectAll() {
        List<RentalContract> rentalContracts = new ArrayList<>();
        String sql = "SELECT * FROM RentalContract";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rentalContracts.add(new RentalContract(
                        rs.getInt("rental_id"), // Assuming RentalContract has a rentalID
                        rs.getInt("person_id"),
                        rs.getInt("car_id"),
                        rs.getTimestamp("from_datetime"),
                        rs.getTimestamp("to_datetime"),
                        rs.getInt("max_km"),
                        rs.getInt("start_odometer")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalContracts;
    }
}