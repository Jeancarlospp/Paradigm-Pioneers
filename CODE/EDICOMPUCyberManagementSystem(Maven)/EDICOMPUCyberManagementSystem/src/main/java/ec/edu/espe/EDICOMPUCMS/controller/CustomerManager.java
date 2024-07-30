package ec.edu.espe.EDICOMPUCMS.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.EDICOMPUCMS.model.Customer;
import ec.edu.espe.EDICOMPUCMS.model.Users;
import ec.edu.espe.EDICOMPUCMS.model.DatabaseConnection;
import ec.edu.espe.EDICOMPUCMS.utils.PasswordManager;
import org.bson.Document;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private MongoCollection<Document> customerCollection;
    private MongoCollection<Document> userCollection;

    public CustomerManager() {
        MongoDatabase database = DatabaseConnection.getInstance().getDatabase();
        this.customerCollection = database.getCollection("Customers"); // Colección para clientes
        this.userCollection = database.getCollection("Users"); // Colección para usuarios
    }

    public void addCustomer(Customer customer) {
        Document doc = new Document("id", customer.getId())
                .append("name", customer.getName())
                .append("address", customer.getAddress())
                .append("phone", customer.getPhone())
                .append("email", customer.getEmail());
        customerCollection.insertOne(doc);
    }

    public void addUser(Users user) {
        String encryptedPassword = PasswordManager.encrypt(user.getPassword());
        Document doc = new Document("username", user.getUsername())
                .append("password", encryptedPassword); // La contraseña ya está encriptada
        userCollection.insertOne(doc);
    }

    public Users getUser(String username) {
        Document doc = userCollection.find(Filters.eq("username", username)).first();
        if (doc != null) {
            return new Users(doc.getString("username"), PasswordManager.decrypt(doc.getString("password")));
        }
        return null;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (Document doc : customerCollection.find()) {
            customers.add(new Customer(
                    doc.getString("id"),
                    doc.getString("name"),
                    doc.getString("address"),
                    doc.getString("phone"),
                    doc.getString("email")
            ));
        }
        return customers;
    }

    public void updateCustomer(Customer customer) {
        Document updateDoc = new Document("$set", new Document("name", customer.getName())
                .append("address", customer.getAddress())
                .append("phone", customer.getPhone())
                .append("email", customer.getEmail()));
        customerCollection.updateOne(Filters.eq("id", customer.getId()), updateDoc);
    }

    public void removeCustomer(String id) {
        customerCollection.deleteOne(Filters.eq("id", id));
    }
}
