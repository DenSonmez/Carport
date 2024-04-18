package dat.backend.model.persistence;

import dat.backend.model.entities.OrderView;
import dat.backend.model.entities.Orders;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;


public class AdminFacade {

    public static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        return AdminMapper.getAllUsers(connectionPool);
    }

    public static List<Orders> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        return AdminMapper.getAllOrders(connectionPool);
    }


    public static List<OrderView> getAllOrdersAndUserInfo(ConnectionPool connectionPool) throws DatabaseException {
        return AdminMapper.getAllOrdersAndUserInfo(connectionPool);
    }

}