package ec.edu.espe.EDICOMPUCMS.model;
/**
 *
 * Paradigm Pioneers Squad, DCCO-ESPE
 */
public class Customer {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;



    public Customer(String id, String name, String address, String phone, String email) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "id='" + id + '\'' +", name='" + name + '\'' +", address='" + address + '\'' +", phone='" + phone + '\'' +       ", email='" + email + '\'' +'}';
    }
}
