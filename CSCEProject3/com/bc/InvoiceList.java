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

	public InvoiceList(PersonList people, CustomerList customers, ProductList products) {
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

		String personQuery = "select a.invoiceCode, b.personCode, a.invoiceId from Invoice_156 as a join Person_156 as b on a.personId = b.personId;";
		String customerQuery = "select b.customerCode from Invoice_156 as a join Customer_156 as b on a.customerId = b.customerId;";
		String rentalQuery = "select a.invoiceProductId, b.rentalCode, a.costMultiplier from InvoiceProduct_156 as a join Rental_156 as b on a.productId = b.productId where a.productId = ?;";
		String repairQuery = "select a.invoiceProductId, b.repairCode, a.costMultiplier from InvoiceProduct_156 as a join Repair_156 as b on a.productId = b.productId where a.productId = ?;";
		String concessionQuery = "select a.invoiceProductId, b.concessionCode, a.costMultiplier, a.associatedRepairCode from InvoiceProduct_156 as a join Concession_156 as b on a.productId = b.productId where a.productId = ?;";
		String towingQuery = "select a.invoiceProductId, b.towCode, a.costMultiplier from InvoiceProduct_156 as a join Tow_156 as b on a.productId = b.productId where a.productId = ?;";

		
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psTwo = null;
		ResultSet rsTwo = null;
		PreparedStatement psThree = null;
		ResultSet rsThree = null;
		
		String invoiceCode = null;
		Integer invoiceId = null;
		String personCode = null;
		String customerCode = null;
		String productCode = null;
		Double multiplier = null;
		String associatedCode = null;
		int count = 0;

		try {
			ps = conn.prepareStatement(personQuery);
			rs = ps.executeQuery();
			psTwo = conn.prepareStatement(customerQuery);
			rsTwo = psTwo.executeQuery();
			while (rs.next()) {
				rsTwo.next();
				invoiceCode = rs.getString("a.invoiceCode");
				invoiceId = rs.getInt("a.invoiceId");
				personCode = rs.getString("b.personCode");
				customerCode = rsTwo.getString("b.customerCode");
				this.listOfObjects.add(new Invoice(invoiceCode, personCode, customerCode, people, customers, products));
				
				psThree = conn.prepareStatement(rentalQuery);
				psThree.setInt(1, invoiceId);
				rsThree = psThree.executeQuery();
				while(rsThree.next()) {
					productCode = rsThree.getString("b.rentalCode");
					multiplier = rsThree.getDouble("a.costMultiplier");
					listOfObjects.get(count).addProduct(productCode, multiplier, associatedCode);
				}
				
				psThree = conn.prepareStatement(repairQuery);
				psThree.setInt(1, invoiceId);
				rsThree = psThree.executeQuery();
				while(rsThree.next()) {
					productCode = rsThree.getString("b.repairCode");
					multiplier = rsThree.getDouble("a.costMultiplier");
					listOfObjects.get(count).addProduct(productCode, multiplier, associatedCode);
				}
				
				psThree = conn.prepareStatement(towingQuery);
				psThree.setInt(1, invoiceId);
				rsThree = psThree.executeQuery();
				while(rsThree.next()) {
					productCode = rsThree.getString("b.towCode");
					multiplier = rsThree.getDouble("a.costMultiplier");
					listOfObjects.get(count).addProduct(productCode, multiplier, associatedCode);
				}
				
				psThree = conn.prepareStatement(concessionQuery);
				psThree.setInt(1, invoiceId);
				rsThree = psThree.executeQuery();
				while(rsThree.next()) {
					productCode = rsThree.getString("b.concessionCode");
					multiplier = rsThree.getDouble("a.costMultiplier");
					associatedCode = rsThree.getString("a.associatedRepairCode");
					listOfObjects.get(count).addProduct(productCode, multiplier, associatedCode);
					associatedCode = null;
				}
				
				count ++;	
			}
			
			rs.close();
			ps.close();
			rsTwo.close();
			psTwo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			// If statement is in case they are not null or already closed
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (rsTwo != null && !rsTwo.isClosed())
				rsTwo.close();
			if (psTwo != null && !psTwo.isClosed())
				psTwo.close();
			if (rsThree!= null && !rsThree.isClosed())
				rsThree.close();
			if (psThree != null && !psThree.isClosed())
				psThree.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
