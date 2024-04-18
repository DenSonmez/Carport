package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.services.RegisterHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    // TODO: Change mysql login credentials if needed below

    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement statement = testConnection.createStatement()) {
                // Create test database - if not exist
                statement.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");

                // TODO: Create user table. Add your own tables here
                statement.execute("CREATE TABLE IF NOT EXISTS carport_test.user LIKE carport.user;");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement statement = testConnection.createStatement()) {
                statement.execute("delete from itemlist");
                statement.execute("delete from orders");
                statement.execute("delete from user");

                // TODO: Insert a few users - insert rows into your own tables here
                statement.execute("insert into user (email, password, firstname, lastname, phonenumber) " +
                        "values ('user@mail.com','"+ RegisterHelper.hashPassword("1234") + "','user', 'vic','245534'),('admin@mail.com','"+ RegisterHelper.hashPassword("1234") + " ','admin','den', '744554'), ('ben@mail.com',' "+ RegisterHelper.hashPassword("1234") + " ','user', 'ras', '647476')");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void login() throws DatabaseException {
        User expectedUser = new User("user@mail.com", "1234", "user", "vic", 245534);
        User actualUser = UserFacade.login("user@mail.com", "1234", connectionPool);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> UserFacade.login("user@mail.com", "123", connectionPool));
    }

    @Test
    void invalidUserNameLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> UserFacade.login("bob@mail.com", "1234", connectionPool));
    }

    @Test
    void createUser() throws DatabaseException {
        User newUser = UserFacade.createUser("ben@mail.com","1234","user", "ras", 647476,  connectionPool);
        User logInUser = UserFacade.login("ben@mail.com", "1234", connectionPool);
        User expectedUser = new User("ben@mail.com","1234","user", "ras", 647476);
        assertEquals(expectedUser, newUser);
        assertEquals(expectedUser, logInUser);

    }

}