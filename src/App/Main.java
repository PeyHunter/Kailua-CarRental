package App;
import java.sql.Date;
import Models.Car;
import Database.DataBaseConnection;
import Database.DataBaseQuery;


import java.sql.Connection;


public class Main
{
    public static void main(String[] args)
    {

        //CONNECTION TO DATABASE
        try (Connection conn = DataBaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Fetching records...");
        DataBaseQuery.fetchAllRecords();

      //  Car car = new Car(1, "Honda", "Gazelle", "Gas", "DB827", new Date(System.currentTimeMillis(), 12, 1);






    }
}