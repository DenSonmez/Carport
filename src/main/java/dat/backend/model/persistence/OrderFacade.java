package dat.backend.model.persistence;

import dat.backend.model.entities.Orders;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class OrderFacade {



    public static int createOrder(Orders orders, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.createOrder(orders, connectionPool);
    }



    public static void removeOrderById(int id, ConnectionPool connectionPool) throws DatabaseException{
        OrderMapper.removeOrderById(id, connectionPool);
    }
    public static void setPrice(int id, float price, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.setPrice(id, price, connectionPool);

    }
   public static Orders getOrderById(int id, ConnectionPool connectionPool) throws DatabaseException {

        return OrderMapper.getOrderById(id, connectionPool);
    }


}
