package dat.backend.model.persistence;

import dat.backend.model.entities.OrderView;
import dat.backend.model.entities.Orders;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


 class AdminMapper {

   protected static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM user;";

        List<User> userList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int phoneNumber = rs.getInt("phonenumber");
                    String role = rs.getString("role");

                    User user = new User(userId, email, password, firstname, lastname, phoneNumber, role);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return userList;
    }


    protected static List<Orders> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "SELECT * FROM orders;";

        List<Orders> orderList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int orderId = rs.getInt("user_id");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    int totalPrice = rs.getInt("price");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");

                    Orders order = new Orders(id, orderId, timestamp, totalPrice, length, width);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return orderList;
    }

    protected static List<OrderView> getAllOrdersAndUserInfo(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "SELECT orders.*, user.email FROM orders INNER JOIN user ON orders.user_id = user.user_id;";

        List<OrderView> orderViewList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    int totalPrice = rs.getInt("price");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    String email = rs.getString("email");

                    OrderView order = new OrderView(orderId, userId, timestamp, totalPrice, length, width, email);
                    orderViewList.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return orderViewList;
    }



}

