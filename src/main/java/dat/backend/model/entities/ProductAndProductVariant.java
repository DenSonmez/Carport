package dat.backend.model.entities;

public class ProductAndProductVariant {
    private String name;
    private String description;
    private Unit unit;
    private float pricePrUnit;
    private ProductType type;
    private int productId;
    private int productVariantId;
    private float length;
    private float width;
    private float height;

    public ProductAndProductVariant(String name, String description, Unit unit, float pricePrUnit, ProductType type, int productId, int productVariantId, float length, float width, float height) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.pricePrUnit = pricePrUnit;
        this.type = type;
        this.productId = productId;
        this.productVariantId = productVariantId;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Unit getUnit() {
        return unit;
    }

    public float getPricePrUnit() {
        return pricePrUnit;
    }

    public ProductType getType() {
        return type;
    }

    public int getProductId() {
        return productId;
    }

    public int getProductVariantId() {
        return productVariantId;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }
}
