package com.highradius.implementation;

import com.highradius.model.Invoice;
import com.highradius.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao {

	// Initializing Properties
	private Connection connection = null;
	private Statement queryStatement = null;
	private PreparedStatement prepStatement = null;
	private ResultSet rs = null;

	// Method to call DatabaseConnection addInvoice method
	@Override
	public void insertInvoice(Invoice invoice) {

		// Creating Statement
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection status = DatabaseConnection.Connect();

			if (status != null) {
				System.out.println("Connection to the Database established!IMPL");
			}

			String query = "INSERT INTO h2h_oap VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			prepStatement = connection.prepareStatement(query);

			prepStatement.setInt(1, invoice.getSlNo());
			prepStatement.setInt(2, invoice.getCustomerOrderID());
			prepStatement.setInt(3, invoice.getSalesOrg());
			prepStatement.setString(4, invoice.getDistributionChannel());
			prepStatement.setInt(5, invoice.getCustomerNumber());
			prepStatement.setInt(6, invoice.getCompanyCode());
			prepStatement.setString(7, invoice.getOrderCurrency());
			prepStatement.setDouble(8, invoice.getAmountInUSD());
			prepStatement.setDouble(9, invoice.getOrderAmount());
			prepStatement.setDate(10, new java.sql.Date(invoice.getOrderCreationDate().getTime()));

			// Execute the INSERT statement
			prepStatement.executeUpdate();

			// Adding to the list in dbConnection object
			DatabaseConnection.addInvoice(invoice);

			System.out.println("Row added successfully!"); // Displaying row was added
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	// Method to call DatabaseConnection getInvoices method
	public List<Invoice> getInvoice() {

		// Creating Statement
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection status = DatabaseConnection.Connect();

			if (status != null) {
				System.out.println("Connection to the Database established!IMPL");
			}

			queryStatement = connection.createStatement();

			// Executing Query
			rs = queryStatement.executeQuery("Select * FROM h2h_oap");
			int count = 0; // To count the total no of rows added

			// Iterating through rows of the table
			while (rs.next()) {
				String creationdate = rs.getString(9);
				DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
				Date orderCreationDate = df.parse(creationdate);

				// Creating an Invoice class object with data from table
				Invoice inv = new Invoice(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(17),
						rs.getInt(8), rs.getString(15), rs.getInt(18), rs.getInt(13), orderCreationDate);

				// Adding the Invoice object 'inv' to the list of invoices
				DatabaseConnection.addInvoice(inv);

				// Incrementing the value of count each time
				count++;
				System.out.println(count + " rows loaded successfully!"); // Displaying how many total rows were added
			}
		} catch (ParseException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		// Returning the list from dbConnection object
		return DatabaseConnection.getInvoices();
	}

	// Method to update invoice
	@Override
	public void updateInvoice(int id, Invoice invoice) {

		// Creating Statement
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection status = DatabaseConnection.Connect();

			if (status != null) {
				System.out.println("Connection to the Database established!IMPL");
			}

			String query = "UPDATE invoices SET " + "customer_order_id = ?, " + "sales_org = ?, "
					+ "distribution_channel = ?, " + "customer_number = ?, " + "company_code = ?, "
					+ "order_currency = ?, " + "amount_in_usd = ?, " + "order_amount = ?, " + "order_creation_date = ? "
					+ "WHERE sl_no = ?";

			prepStatement = connection.prepareStatement(query);

			prepStatement.setInt(1, invoice.getCustomerOrderID());
			prepStatement.setInt(2, invoice.getSalesOrg());
			prepStatement.setString(3, invoice.getDistributionChannel());
			prepStatement.setInt(4, invoice.getCustomerNumber());
			prepStatement.setInt(5, invoice.getCompanyCode());
			prepStatement.setString(6, invoice.getOrderCurrency());
			prepStatement.setDouble(7, invoice.getAmountInUSD());
			prepStatement.setDouble(8, invoice.getOrderAmount());
			prepStatement.setDate(9, new java.sql.Date(invoice.getOrderCreationDate().getTime()));
			prepStatement.setInt(10, id);

			// Execute the INSERT statement
			prepStatement.executeUpdate();

			System.out.println("Row modified successfully!"); // Displaying row was modified
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Method to delete invoice
	@Override
	public void deleteInvoice(int id) {

		// Creating Statement
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection status = DatabaseConnection.Connect();

			if (status != null) {
				System.out.println("Connection to the Database established!IMPL");
			}

			String query = "DELETE FROM h2h_oap WHERE sl_no=?;";

			prepStatement = connection.prepareStatement(query);

			prepStatement.setInt(1, id);

			// Execute the INSERT statement
			prepStatement.executeUpdate();

			System.out.println("Row deleted successfully!"); // Displaying row was deleted
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
