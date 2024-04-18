package dat.backend.model.entities;

public class BillingAddress {

    private int id;
    private int user_id;
    private String address;
    private String city;
    private int zipcode;

    public BillingAddress(int id, int user_id, String address, String city, int zipcode) {
        this.id = id;
        this.user_id = user_id;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
