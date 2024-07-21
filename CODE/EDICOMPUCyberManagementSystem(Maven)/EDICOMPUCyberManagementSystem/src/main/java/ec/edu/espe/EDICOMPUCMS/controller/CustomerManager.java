package ec.edu.espe.EDICOMPUCMS.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import ec.edu.espe.EDICOMPUCMS.model.Customer;
import ec.edu.espe.EDICOMPUCMS.model.DatabaseConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private MongoCollection<Document> collection;

    public CustomerManager() {
        MongoDatabase database = DatabaseConnection.getInstance().getDatabase();
        this.collection = database.getCollection("Customers");
    }

    public void addCustomer(Customer customer) {
        Document doc = new Document("id", customer.getId())
                .append("name", customer.getName())
                .append("address", customer.getAddress())
                .append("phone", customer.getPhone())
                .append("email", customer.getEmail());
        collection.insertOne(doc);
    }

    public void removeCustomer(String id) {
        collection.deleteOne(Filters.eq("id", id));
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (Document doc : collection.find()) {
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
        collection.updateOne(Filters.eq("id", customer.getId()), updateDoc);
    }
}