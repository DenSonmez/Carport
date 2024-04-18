package dat.backend.model.entities;

public class CompleteProduct {

    String name;
    float length;
    int amount;
    Unit unit;
    String description;
    int productVariantId;
    int productId;

    public CompleteProduct(String name, float length, int amount, Unit unit, String description, int productVariantId, int productId) {
        this.name = name;
        this.length = length;
        this.amount = amount;
        this.unit = unit;
        this.description = description;
        this.productVariantId = productVariantId;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductVariantId()
    {
        return productVariantId;
    }

    public int getProductId()
    {
        return productId;
    }
}
