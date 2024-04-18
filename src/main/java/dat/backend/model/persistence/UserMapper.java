package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.services.RegisterHelper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserMapper {
    protected static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, RegisterHelper.hashPassword(password));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    int phonenumber = rs.getInt("phonenumber");
                    String role = rs.getString("role");

                    user = new User(userId, email, password, firstname, lastname, phonenumber, role);
                } else {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    protected static User createUser(String email, String password, String firstname, String lastname, int phonenumber, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (email, password, firstname, lastname, phonenumber) values (?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, email);
                ps.setString(2, RegisterHelper.hashPassword(password));
                ps.setString(3, firstname);
                ps.setString(4, lastname);
                ps.setInt(5, phonenumber);
                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rowsAffected == 1) {
                    int userId = -1;
                    String role = "user";
                    if (rs.next()) {
                        userId = rs.getInt(1);
                    }
                    user = new User(userId, email, password, firstname, lastname, phonenumber, role);
                    return user;
                } else {
                    throw new DatabaseException("The user with email = " + email + " could not be inserted into the database");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }

    }


}
