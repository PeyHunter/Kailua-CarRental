package DAO;

import Models.CarGroup; // Import your CarGroup model
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarGroupDAO implements GenericDAO<CarGroup> {
    private Connection connection;

    // Constructor to initialize the database connection
    public CarGroupDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(CarGroup carGroup) {
        String sql = "INSERT INTO car_group (group_name, description) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, carGroup.getGroupName());
            pstmt.setString(2, carGroup.getDescription());
            pstmt.executeUpdate();

            // Retrieve the generated carGroupID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                carGroup.setCarGroupID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CarGroup carGroup) {
        String sql = "UPDATE car_group SET group_name = ?, description = ? WHERE car_group_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, carGroup.getGroupName());
            pstmt.setString(2, carGroup.getDescription());
            pstmt.setInt(3, carGroup.getCarGroupID()); // Use carGroupID to find the correct group

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Car group updated successfully.");
            } else {
                System.out.println("No car group found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM car_group WHERE car_group_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Car group deleted successfully.");
            } else {
                System.out.println("No car group found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CarGroup selectById(int id) {
        String sql = "SELECT * FROM car_group WHERE car_group_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new CarGroup(
                        rs.getInt("car_group_id"), // Assuming CarGroup has a constructor that takes carGroupID
                        rs.getString("group_name"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CarGroup> selectAll() {
        List<CarGroup> carGroups = new ArrayList<>();
        String sql = "SELECT * FROM car_group";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                carGroups.add(new CarGroup(
                        rs.getInt("car_group_id"), // Assuming CarGroup has a carGroupID
                        rs.getString("group_name"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carGroups;
    }
}