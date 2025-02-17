import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection
{

    public static final String URL = "jdbc:mysql://localhost:3306/CarRental";
    public static final String USER = "root";
    public static final String PASSWORD = "Kea=Krea1994";

    public static Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error connection to the database", e);
        }
    }
}