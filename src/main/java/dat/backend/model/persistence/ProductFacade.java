package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class ProductFacade {
    public static List<ProductAndProductVariant> getAllProducts(ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.getAllProducts(connectionPool);
    }

    public static ProductAndProductVariant getProduct(int id, ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.getProduct(id, connectionPool);
    }


    public static void removeProductList(int id, ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper.removeProductList(id, connectionPool);
    }

    public static void removeProductVariant(int id, ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper.removeProductVariant(id, connectionPool);
    }

    public static int addProduct(String name, String description, Unit unit, float pricePrUnit, ProductType type, ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.addProduct(name, description, unit, pricePrUnit, type, connectionPool);
    }

    public static int addProductVariant(int productId, float length, float width, float height, ConnectionPool connectionPool) throws DatabaseException {
        return ProductMapper.addProductVariant(productId ,length, width, height, connectionPool);
    }

    public static void editProduct(String name, int id, String description, Unit unit, float pricePerUnit, ProductType type, ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper.editProduct(name, id, description, unit, pricePerUnit, type, connectionPool);
    }

    public static void editProductVariant(float height, float width, float length, int productId, int id, ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper.editProductVariant(height, width, length, productId, id, connectionPool);
    }
    public static ProductVariant getProductVariantById(int productVaraintId, ConnectionPool connectionPool) throws DatabaseException
    {
        return ProductMapper.getProductVariantById(productVaraintId, connectionPool);
    }
}
