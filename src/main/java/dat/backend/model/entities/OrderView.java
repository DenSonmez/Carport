package dat.backend.model.entities;

import java.sql.Timestamp;

public class OrderView {
    private int orderId;
    private int user_id;
    private Timestamp timestamp;
    private float price;
    private int length;
    private int width;
    private String email;

    public OrderView(int orderId, int user_id, Timestamp timestamp, float price, int length, int width, String email) {
        this.orderId = orderId;
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.price = price;
        this.length = length;
        this.width = width;
        this.email = email;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUser_id() {
        return user_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public float getPrice() {
        return price;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getEmail() {
        return email;
    }
}
