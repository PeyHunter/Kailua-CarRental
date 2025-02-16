
import java.sql.Connection;

public class Main
{
    public static void main(String[] args)
    {

        try (Connection conn = DataBaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Fetching records...");
        DataBaseQuery.fetchAllRecords();


    }
}