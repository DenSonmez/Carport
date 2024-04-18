package dat.backend.model.services;

import dat.backend.model.entities.ItemEntry;
import dat.backend.model.persistence.ConnectionPool;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class CarportBuilderHelperTest {

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
    public void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement statement = testConnection.createStatement()) {
                // TODO: Remove all rows from all tables - add your own tables here
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
    public void testGetPostAmount() {
        CarportBuilderHelper helper = new CarportBuilderHelper();
        int length = 500;
        int width = 560;
        int expectedPostAmount = 6;
        List<ItemEntry> actualPostAmount = helper.getPostAmount(width, length, 3);
        Assertions.assertEquals(expectedPostAmount, actualPostAmount.size());

    }

    @Test
    public void testGetStrapAmount() {
        CarportBuilderHelper helper = new CarportBuilderHelper();
        int length = 500;
        int expectedStrapAmount = 2;
        List<ItemEntry> actualStrapAmount = helper.getStrapAmount(length, 3);
        Assertions.assertEquals(expectedStrapAmount, actualStrapAmount.size());
    }


    @Test
    public void testGetSternFrontAndBackAmount() {
        int width = 500;
        int orderId = 1;
        int expectedAmount = 2;

        List<ItemEntry> itemEntryList = CarportBuilderHelper.getSternFrontAndBackAmount(width, orderId);

        Assertions.assertEquals(expectedAmount, itemEntryList.size());
        for (ItemEntry itemEntry : itemEntryList) {
            Assertions.assertEquals(1, itemEntry.getProductVariantId());
        }
    }

    @Test
    public void testGetSideSternAmount() {
        int length = 540;
        int orderId = 1;
        int expectedAmount = 3;

        List<ItemEntry> itemEntryList = CarportBuilderHelper.getSideSternAmount(length, orderId);
        Assertions.assertEquals(expectedAmount, itemEntryList.size());
        Assertions.assertEquals(5, itemEntryList.get(0).getProductVariantId());
        Assertions.assertEquals(5, itemEntryList.get(1).getProductVariantId());
        Assertions.assertEquals(5, itemEntryList.get(2).getProductVariantId());
    }

    @Test
    public void testGetRaftersAmount() {
        int width = 300;
        int length = 500;
        int orderId = 1;
        int expectedAmount = 4;

        List<ItemEntry> itemEntryList = CarportBuilderHelper.getRaftersAmount(width, length, orderId);
        Assertions.assertEquals(expectedAmount, itemEntryList.size());
        Assertions.assertEquals(10, itemEntryList.get(0).getProductVariantId());
        Assertions.assertEquals(10, itemEntryList.get(1).getProductVariantId());
    }

    @Test
    public void testGetRafterDistance() {
        int length = 500;
        int amount = 3;
        float expectedDistance = 100.0f;

        float actualDistance = CarportBuilderHelper.getRafterDistance(length, amount);
        Assertions.assertEquals(expectedDistance, actualDistance);
    }


    @Test
    public void testGetRoofAmount() {
        int width = 300;
        int length = 500;
        int orderId = 1;
        int expectedAmount = 4;

        List<ItemEntry> itemEntryList = CarportBuilderHelper.getRoofAmount(width, length, orderId);
        Assertions.assertEquals(expectedAmount, itemEntryList.size());
        Assertions.assertEquals(16, itemEntryList.get(0).getProductVariantId());
        Assertions.assertEquals(16, itemEntryList.get(1).getProductVariantId());

    }


    @Test
    public void testGetPostBoltAmount() {
        int postAmount = 4;
        int strapAmount = 4;
        int orderId = 1;
        int expectedAmount = postAmount * strapAmount *2;

        List<ItemEntry> itemEntryList = CarportBuilderHelper.getPostBoltAmount(postAmount, strapAmount, orderId);
        Assertions.assertEquals(expectedAmount, itemEntryList.size());

        for (ItemEntry itemEntry : itemEntryList) {
            Assertions.assertTrue(itemEntry.getProductVariantId() == 14 || itemEntry.getProductVariantId() == 19);
        }
    }

    @Test
    public void testGetBracketAmount() {
        int raftersAmount = 5;
        int orderId = 1;
        int expectedAmount = raftersAmount * 2;

        List<ItemEntry> itemEntryList = CarportBuilderHelper.getBracketAmount(raftersAmount, orderId);
        Assertions.assertEquals(expectedAmount, itemEntryList.size());

        for (ItemEntry itemEntry : itemEntryList) {
            Assertions.assertTrue(itemEntry.getProductVariantId() == 11 || itemEntry.getProductVariantId() == 18);
        }
    }
    @Test
    public void testGetScrewsAmount() {
        int orderId = 1;
        int expectedAmount = 1;

        List<ItemEntry> itemEntryList = CarportBuilderHelper.getScrewsAmount(orderId);
        int actualAmount = itemEntryList.size();

        Assertions.assertEquals(expectedAmount, actualAmount);
    }
    @Test
    public void testGetHollowtiesAmount() {
        int width = 500;
        int length = 800;
        int orderId = 1;
        int expectedAmount = 2;

        List<ItemEntry> itemEntryList = CarportBuilderHelper.getHollowtiesAmount(width, length, orderId);
        int actualAmount = itemEntryList.size();

        Assertions.assertEquals(expectedAmount, actualAmount);
    }
    @Test
    public void testGetBracketScrewsAmount() {
        int raftersAmount = 15;
        int orderId = 1;
        int expectedAmount = 3;
        int expectedProductVariantId = 21;

        List<ItemEntry> itemEntryList = CarportBuilderHelper.getBracketScrewsAmount(raftersAmount, orderId);
        Assertions.assertEquals(expectedAmount, itemEntryList.size());
        for (ItemEntry itemEntry : itemEntryList) {
            Assertions.assertEquals(expectedProductVariantId, itemEntry.getProductVariantId());
        }
    }

    @Test
    public void testStartPrice() {
        int width = 500;
        int length = 400;
        float expectedPrice = 160.0f;

        float actualPrice = CarportBuilderHelper.startPrice(width, length) / 100;  // jeg deler med 100 for at konverter det til kvadratmeter
        Assertions.assertEquals(expectedPrice, actualPrice);
    }


}





