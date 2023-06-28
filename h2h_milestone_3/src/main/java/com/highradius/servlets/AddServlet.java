package com.highradius.servlets;

import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	  // Retrieve the values from the form using request.getParameter()

    	  int slNo = Integer.parseInt(request.getParameter("slNo"));
    	  int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
    	  int salesOrg = Integer.parseInt(request.getParameter("salesOrg"));
    	  String distributionChannel = request.getParameter("distributionChannel");
    	  String division = request.getParameter("division");
    	  double releasedCreditValue = Double.parseDouble(request.getParameter("releasedCreditValue"));
    	  String purchaseOrderType = request.getParameter("purchaseOrderType");
    	  int companyCode = Integer.parseInt(request.getParameter("companyCode"));
    	  String orderCreationDate = request.getParameter("orderCreationDate");
    	  String orderCreationTime = request.getParameter("orderCreationTime");
    	  String creditControlArea = request.getParameter("creditControlArea");
    	  int soldToParty = Integer.parseInt(request.getParameter("soldToParty"));
    	  double orderAmount = Double.parseDouble(request.getParameter("orderAmount"));
    	  String requestedDeliveryDate = request.getParameter("requestedDeliveryDate");
    	  String orderCurrency = request.getParameter("orderCurrency");
    	  String creditStatus = request.getParameter("creditStatus");
    	  int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
    	  double amountInUsd = Double.parseDouble(request.getParameter("amountInUsd"));
    	  long uniqueCustId = Long.parseLong(request.getParameter("uniqueCustId"));

    	  Invoice invoice = new Invoice(slNo, customerOrderId, salesOrg, distributionChannel, division, releasedCreditValue, purchaseOrderType,
    			    companyCode, orderCreationDate, orderCreationTime, creditControlArea, soldToParty, orderAmount, requestedDeliveryDate,
    			    orderCurrency, creditStatus, customerNumber, amountInUsd, uniqueCustId);


          InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
          invoiceDao.addInvoice(invoice);

          // Redirect back to the original HTML form page
          response.sendRedirect("text.html");
          System.out.println("Database updated");
          
          String classpath = System.getProperty("java.class.path");
          System.out.println("Classpath: " + classpath);
          
          
          invoiceDao.getInvoices();                
              	}
   }
