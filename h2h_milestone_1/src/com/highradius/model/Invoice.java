package com.highradius.model;

public class Invoice {
    private String invoiceNumber;
    private String customerName;
    private double amount;

    // Parameterized constructor
    public Invoice(String invoiceNumber, String customerName, double amount) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.amount = amount;
    }

    // Getter for invoiceNumber
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    // Setter for invoiceNumber
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    // Getter for customerName
    public String getCustomerName() {
        return customerName;
    }

    // Setter for customerName
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter for amount
    public double getAmount() {
        return amount;
    }

    // Setter for amount
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
