package Test;

import DAO.PersonDAO;
import Database.DataBaseConnection;
import Models.Person;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {
    private static Connection connection;
    private static PersonDAO personDAO;

    @BeforeAll
    static void setUp() {
        connection = DataBaseConnection.getConnection(); // Get DB connection
        personDAO = new PersonDAO(connection);
    }

    @Test
    void testInsertPerson() {
        // Create a test person
        Person person = new Person("John Doe", "12345678", "john@example.com", "DL12345", new Date(), 1);

        // Try inserting into DB
        assertDoesNotThrow(() -> personDAO.insert(person), "Inserting person should not throw an exception");
    }

    @AfterAll
    static void tearDown() throws Exception {
        connection.close(); // Close connection after tests
    }
}