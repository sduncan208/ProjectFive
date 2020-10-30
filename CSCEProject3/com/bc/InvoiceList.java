/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The list for invoices
 */

package com.bc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bc.ext.DatabaseInfo;

public class InvoiceList implements ListInterface<Invoice>{
	private ArrayList<Invoice> listOfObjects = new ArrayList<Invoice>();

	public InvoiceList(ArrayList<String> inputList, PersonList people, CustomerList customers, ProductList products) {
		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(DRIVER_CLASS).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String personQuery = "select a.invoiceCode, b.personCode from Invoice_156 as a join Person_156 as b on a.personId = b.personId;";
		String customerQuery = "select b.customerCode from Invoice_156 as a join Customer_156 as b on a.customerId = b.customerId;";
		String productQuery = "select b.productId from Invoice_156 as a join InvoiceProduct_156 as b on a.invoiceId = b.invoiceId;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String invoiceCode = null;
		String personCode = null;
		String customerCode = null;
		String productCode = null;

		try {
			ps = conn.prepareStatement(personQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				this.listOfObjects.add(new Invoice());
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			// If statement is in case they are not null or already closed
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < inputList.size(); i++) {
			String invoiceItem = inputList.get(i);
			String[] invoiceList = invoiceItem.split(";");

			// Creates an invoice and adds it to the InvoiceList
			this.listOfObjects.add(new Invoice(invoiceList[0], invoiceList[1], invoiceList[2], invoiceList[3], people, customers, products));
		}
	}
	
	public int getSize() {
		return this.listOfObjects.size();
	}
	
	public Invoice getObject(int index) {
		return this.listOfObjects.get(index);
	}

	public Invoice codeToObject(String code) {
		for(Invoice x : this.listOfObjects) {
			if(x.getInvoiceCode().equals(code))
				return x;
		}
		return null;
	}
}
