package App;
import java.sql.Date;


import Models.Car;
import Database.DataBaseConnection;
import Database.DataBaseQuery;

import Models.Person;
import DAO.PersonDAO;


import java.sql.Connection;


public class Main
{
    public static void main(String[] args)
    {

        //CONNECTION TO DATABASE
        try (Connection conn = DataBaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Database connection failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



      //  Car car = new Car(1, "Honda", "Gazelle", "Gas", "DB827", new Date(System.currentTimeMillis(), 12, 1);






    }
}