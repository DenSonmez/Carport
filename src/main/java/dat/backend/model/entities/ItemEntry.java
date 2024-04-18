package dat.backend.model.entities;

public class ItemEntry {

    private int id;
    private int orderId;
    private int productVariantId;

    public ItemEntry(int id, int orderId, int productVariantId) {
        this.id = id;
        this.orderId = orderId;
        this.productVariantId = productVariantId;
    }

    public ItemEntry(int orderId, int productVariantId) {
        this.id = -1;
        this.orderId = orderId;
        this.productVariantId = productVariantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(int productVariantId) {
        this.productVariantId = productVariantId;
    }
}
