package com.highradius.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.highradius.connection.DatabaseConnection;
import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

/**
 * Servlet implementation class DataLoadingServlet
 */
@WebServlet("/dataloadingservlet")
public class DataLoadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DataLoadingServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection status = DatabaseConnection.Connect();

		if (status != null) {
			System.out.println("Connection to the Database established!Ld");
		}

		String jsonResponse = new String();
		PrintWriter out = response.getWriter();
		System.out.println();

		List<Invoice> invoices = getAllInvoices();
		out.println(invoices);

		out.println();

		Gson gson = new Gson();
		jsonResponse = gson.toJson(invoices);
		out.println(jsonResponse);
		System.out.println(jsonResponse);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	// Method to get all Invoices data using InvoiceDao getInvoice method
	protected static List<Invoice> getAllInvoices() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection status = DatabaseConnection.Connect();

		if (status != null) {
			System.out.println("Connection to the Database established!Ld");
		}
		// Create an instance of InvoiceDao
		InvoiceDao invoiceDao = new InvoiceDaoImpl();

		// Retrieve all invoices using the InvoiceDao
		List<Invoice> invoices = invoiceDao.getInvoice();

		return invoices;
	}

}
