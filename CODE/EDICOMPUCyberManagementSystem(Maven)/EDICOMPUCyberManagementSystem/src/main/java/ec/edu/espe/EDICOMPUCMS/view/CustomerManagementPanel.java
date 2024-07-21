package ec.edu.espe.EDICOMPUCMS.view;

import ec.edu.espe.EDICOMPUCMS.controller.CustomerManager;
import ec.edu.espe.EDICOMPUCMS.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

public class CustomerManagementPanel extends JPanel {
    private CustomerManager customerManager;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, addressField, phoneField, emailField, searchField;
    private JButton addButton, updateButton, deleteButton, clearButton, searchButton;

    public CustomerManagementPanel() {
        customerManager = new CustomerManager();
        setLayout(new BorderLayout());

        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("ID:"));
        idField = new JTextField(10);
        setNumericOnly(idField, 10);
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        formPanel.add(nameField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField(30);
        formPanel.add(addressField);

        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField(10);
        setNumericOnly(phoneField, 10);
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        formPanel.add(emailField);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create table
        String[] columnNames = {"ID", "Name", "Address", "Phone", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        customerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);

        // Add components to panel
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(searchPanel, BorderLayout.WEST);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomer();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomers();
            }
        });

        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = customerTable.getSelectedRow();
                if (selectedRow != -1) {
                    idField.setText((String) tableModel.getValueAt(selectedRow, 0));
                    nameField.setText((String) tableModel.getValueAt(selectedRow, 1));
                    addressField.setText((String) tableModel.getValueAt(selectedRow, 2));
                    phoneField.setText((String) tableModel.getValueAt(selectedRow, 3));
                    emailField.setText((String) tableModel.getValueAt(selectedRow, 4));
                }
            }
        });

        // Load initial data
        loadCustomers();
    }

    private void setNumericOnly(JTextField textField, int maxLength) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (regEx.matcher(text).matches() && newText.length() <= maxLength) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    private void addCustomer() {
        String id = idField.getText();
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID, Name, and Phone are required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customer customer = new Customer(id, name, address, phone, email);
        customerManager.addCustomer(customer);
        clearFields();
        loadCustomers();
        JOptionPane.showMessageDialog(this, "Customer added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = idField.getText();
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID, Name, and Phone are required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customer customer = new Customer(id, name, address, phone, email);
        customerManager.updateCustomer(customer);
        clearFields();
        loadCustomers();
        JOptionPane.showMessageDialog(this, "Customer updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this customer?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            customerManager.removeCustomer(id);
            loadCustomers();
            JOptionPane.showMessageDialog(this, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    private void loadCustomers() {
        tableModel.setRowCount(0);
        for (Customer customer : customerManager.getCustomers()) {
            Object[] row = {customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone(), customer.getEmail()};
            tableModel.addRow(row);
        }
    }

    private void searchCustomers() {
        String searchTerm = searchField.getText().toLowerCase();
        tableModel.setRowCount(0);
        for (Customer customer : customerManager.getCustomers()) {
            if (customer.getName().toLowerCase().contains(searchTerm) ||
                customer.getId().toLowerCase().contains(searchTerm) ||
                customer.getEmail().toLowerCase().contains(searchTerm)) {
                Object[] row = {customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone(), customer.getEmail()};
                tableModel.addRow(row);
            }
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}