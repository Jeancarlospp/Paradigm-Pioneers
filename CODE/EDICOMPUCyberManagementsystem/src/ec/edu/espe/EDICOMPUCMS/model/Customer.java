
package ec.edu.espe.EDICOMPUCMS.model;

public class Customer {
    
    private String id;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    Computer assignedComputer;

    public Customer(String id, String name, String lastName, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.assignedComputer = null;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Computer getAssignedComputer() {
        return assignedComputer;
    }

    public void setAssignedComputer(Computer assignedComputer) {
        this.assignedComputer = assignedComputer;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", address=" + address + ", phone=" + phone + ", email=" + email + ", assignedComputer=" + assignedComputer + '}';
    }

}
