package DAO;

import Models.Address;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AddressDAO implements GenericDAO<Address>
{
    private Connection connection;

    public AddressDAO(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void insert(Address address) {
        String sql = "INSERT INTO Address (street, zip_code, city) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, address.getStreet());
            pstmt.setString(2, address.getZipCode());
            pstmt.setString(3, address.getCity());
            pstmt.executeUpdate();

            // Get the generated address_id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                address.setAddressID(rs.getInt(1)); // Assuming Address class has setAddressId()
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Address address) {
        String sql = "UPDATE Address SET street = ?, zip_code = ?, city = ? WHERE address_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, address.getStreet());
            pstmt.setString(2, address.getZipCode());
            pstmt.setString(3, address.getCity());
            pstmt.setInt(4, address.getAddressID());
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Address updated successfully.");
            } else {
                System.out.println("No address found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Address WHERE address_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Address deleted successfully.");
            } else {
                System.out.println("No address found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address selectById(int id) {
        String sql = "SELECT * FROM Address WHERE address_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Address(
                        rs.getInt("address_id"),
                        rs.getString("street"),
                        rs.getString("zip_code"),
                        rs.getString("city")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Address> selectAll() {
        List<Address> addresses = new ArrayList<>();
        String sql = "SELECT * FROM Address";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                addresses.add(new Address(
                        rs.getInt("address_id"),
                        rs.getString("street"),
                        rs.getString("zip_code"),
                        rs.getString("city")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }
}


