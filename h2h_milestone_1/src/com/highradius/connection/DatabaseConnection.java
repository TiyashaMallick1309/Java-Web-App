package com.highradius.connection;

import com.highradius.model.Invoice;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private List<Invoice> invoices;

    public DatabaseConnection() {
        invoices = new ArrayList<>();
    }

    // Method to get the list of invoices
    public List<Invoice> getInvoices() {
        return invoices;
    }

    // Method to add an invoice to the list
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }
}
