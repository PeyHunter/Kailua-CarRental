import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseQuery {
    public static void fetchAllRecords() {
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM car")) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("car_id") + ", Name: " + rs.getString("model"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}