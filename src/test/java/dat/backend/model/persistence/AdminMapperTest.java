package dat.backend.model.persistence;

import dat.backend.model.entities.OrderView;
import dat.backend.model.entities.Orders;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.services.RegisterHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AdminMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    List<User> userList;

    List<OrderView> orderViewList;

    List<Orders> orderList;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Create test database - if not exist
                stmt.execute("CREATE DATABASE  IF NOT EXISTS carport_test;");
                stmt.execute("DROP TABLE IF EXISTS carport_test.itemlist;");
                stmt.execute("DROP TABLE IF EXISTS carport_test.orders;");
                stmt.execute("DROP TABLE IF EXISTS carport_test.user;");

                // TODO: Create user table. Add your own tables here
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.orders LIKE carport.orders;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.user LIKE carport.user;");
                stmt.execute("CREATE TABLE IF NOT EXISTS carport_test.itemlist LIKE carport.itemlist;");
                stmt.execute("INSERT INTO carport_test.user SELECT * FROM carport.user GROUP BY user_id;");
                stmt.execute("INSERT INTO carport_test.orders SELECT * FROM carport.orders GROUP BY id;");
                stmt.execute("INSERT INTO carport_test.itemlist SELECT * FROM carport.itemlist GROUP BY id;");
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
    void getAllUserTest() throws DatabaseException {

        try {
            userList = AdminFacade.getAllUsers(connectionPool);
            int expectedAmount = 8;
            assertEquals(expectedAmount, userList.size());

        } catch (DatabaseException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
     }
     @Test
    void getAllOrders() throws DatabaseException {

        try {
            orderList = AdminFacade.getAllOrders(connectionPool);
            int expectedAmount = 6; // vil fejle, der er 1 order i db
            assertEquals(expectedAmount, orderList.size());

        } catch (DatabaseException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
     }

     @Test
    void getAllOrderViews() throws DatabaseException {

        try {
            orderViewList = AdminFacade.getAllOrdersAndUserInfo(connectionPool);
            int expectedAmount = 6; // vil fejle, der er 1 orderView i db
            assertEquals(expectedAmount, orderViewList.size());

        } catch (DatabaseException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
     }
}
