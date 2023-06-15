package com.highradius.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.connection.DatabaseConnection;
import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/addservlet")
public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AddServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection status = DatabaseConnection.Connect();

		if (status != null) {
			System.out.println("Connection to the Database established!Ad");
		}

		int slNo = Integer.parseInt(request.getParameter("slNo"));
		int customerOrderID = Integer.parseInt(request.getParameter("customerOrderID"));
		int salesOrg = Integer.parseInt(request.getParameter("salesOrg"));
		String distributionChannel = request.getParameter("distributionChannel");
		int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
		int companyCode = Integer.parseInt(request.getParameter("companyCode"));
		String orderCurrency = request.getParameter("orderCurrency");
		double amountInUSD = Double.parseDouble(request.getParameter("amountInUSD"));
		double orderAmount = Double.parseDouble(request.getParameter("orderAmount"));
		String orderCreationDateStr = request.getParameter("orderCreationDate");

		// Parse orderCreationDate string to Date object
		Date orderCreationDate = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			orderCreationDate = dateFormat.parse(orderCreationDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Call addInvoice function
		addInvoice(slNo, customerOrderID, salesOrg, distributionChannel, customerNumber, companyCode, orderCurrency,
				amountInUSD, orderAmount, orderCreationDate);
	}

	// Method addInvoice with void return type to Add Invoice data using InvoiceDao
	protected static void addInvoice(int slNo, int customerOrderID, int salesOrg, String distributionChannel,
			int customerNumber, int companyCode, String orderCurrency, double amountInUSD, double orderAmount,
			Date orderCreationDate) {
		// Create a new object of Invoice and initialize with the specified values
		final Invoice newInvoice = new Invoice(slNo, customerOrderID, salesOrg, distributionChannel, customerNumber,
				companyCode, orderCurrency, amountInUSD, orderAmount, orderCreationDate);

		// Create an instance of InvoiceDao
		InvoiceDao invoiceDao = new InvoiceDaoImpl();

		// insertInvoice method from InvoiceDao
		invoiceDao.insertInvoice(newInvoice);

	}
}