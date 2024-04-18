package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemListMapper {


    protected static void addItemList(ItemEntry itemEntry, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO itemlist (order_id, product_variant_id) values (?,?);";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, itemEntry.getOrderId());
                ps.setInt(2, itemEntry.getProductVariantId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 0) {
                    throw new DatabaseException("ItemEntry could not be inserted into the database");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }


    }


    protected static List<CompleteProduct> getCompletProduct(Orders order, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT il.id, order_id, product_variant_id, product_id, height, width, length, product.name, description, price_pr_unit, unit, type, count(*) as amount FROM itemlist as il INNER JOIN product_variant as pvar on il.product_variant_id = pvar.id INNER JOIN product as product on pvar.product_id = product.id where order_id = ? group by product_variant_id;";
        List<CompleteProduct> completeProducts = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    float length = rs.getFloat("length");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String unitString = rs.getString("unit");
                    Unit unit = Unit.valueOf(unitString);
                    int amount = rs.getInt("amount");
                    int productVariantId = rs.getInt("product_variant_id");
                    int productId = rs.getInt("product_id");


                    completeProducts.add(new CompleteProduct(name, length, amount, unit, description,productVariantId, productId));

                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return completeProducts;
    }


    protected static void removeItemListOrderId(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM carport.itemlist WHERE order_id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }
}

