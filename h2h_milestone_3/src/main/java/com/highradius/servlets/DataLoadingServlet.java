package com.highradius.servlets;

import com.google.gson.Gson;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/DataLoadingServlet")
public class DataLoadingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type to JSON
        response.setContentType("application/json");
        
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin
        response.setHeader("Access-Control-Allow-Methods", "GET"); // Allow GET requests
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow the Content-Type header

        // Get the PrintWriter object to write the JSON response
        PrintWriter out = response.getWriter();

        // Retrieve the list of invoices from the database
        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
        List<Invoice> invoices = invoiceDao.getInvoices();

        // Convert the list of invoices to JSON using Gson
        Gson gson = new Gson();
        String json = gson.toJson(invoices);

        // Write the JSON response
        out.println(json);
    }
}
