package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

public class UserFacade {
    public static User login(String username, String password, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.login(username, password, connectionPool);
    }

    public static User createUser(String email, String password, String firstname, String lastname, int phonenumber, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.createUser(email, password, firstname, lastname, phonenumber, connectionPool);
    }
}
