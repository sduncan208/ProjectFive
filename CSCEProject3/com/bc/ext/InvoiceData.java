package com.bc.ext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/* DO NOT change or remove the import statements beneath this.
* They are required for the webgrader to run this phase of the project.
* These lines may be giving you an error; this is fine. 
* These are webgrader code imports, you do not need to have these packages.
*/
import com.bc.model.Concession;
import com.bc.model.Invoice;
import com.bc.model.Customer;
import com.bc.model.Towing;

import unl.cse.albums.DatabaseInfo;

import com.bc.model.Person;
import com.bc.model.Product;
import com.bc.model.Rental;
import com.bc.model.Repair;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application. 16 methods in
 * total, add more if required. Do not change any method signatures or the
 * package name.
 * 
 * Adapted from Dr. Hasan's original version of this file
 * 
 * @author Chloe
 *
 */
public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */
	// Joe
	public static void removeAllPersons() {
		/* TODO */
		String url = DatabaseInfo.URL;
		String user = DatabaseInfo.USERNAME;
		String password = DatabaseInfo.PASSWORD;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ps = con.prepareStatement("DELETE FROM InvoiceProduct_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Invoice_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Email_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Customer_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Person_156");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (rs != null && !rs.isClosed()) 
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (con != null && !con.isClosed()) 
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country) {
		/* TODO */
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

		// Gets the personId associated with the personCode
		String query = "select personId from Person_156 where personCode like ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer personKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if (rs.next())
				personKey = rs.getInt("personId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (personKey != null)
			throw new RuntimeException("Please enter a valid personCode");

		// Inserts the new address into Address_156
		Integer addressKey = null;
		query = "INSERT INTO Address_156 (street, city, state, zip, country)" + "VALUES (?, ?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();
			addressKey = rs.getInt(1);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Inserts the new person into Person_156
		query = "INSERT INTO Person_156 (personCode, firstName, lastName, addressID)" + "VALUES (?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt(4, addressKey);
			ps.executeUpdate();

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
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		/* TODO */
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

		// Gets the personId associated with the personCode
		String query = "select personId from Person_156 where personCode like ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer personKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if (rs.next())
				personKey = rs.getInt("personId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (personKey == null)
			throw new RuntimeException("Please enter a valid personCode");

		// Inserts the new email into Email_156
		query = "INSERT INTO Email_156 (emailAddress, personId)" + "VALUES (?, ?)";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setInt(2, personKey);
			ps.executeUpdate();
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
	}

	/**
	 * 4. Method that removes every customer record from the database
	 */

	// Joe
	public static void removeAllCusomters() {
		/* TODO */
		String url = DatabaseInfo.URL;
		String user = DatabaseInfo.USERNAME;
		String password = DatabaseInfo.PASSWORD;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement("DELETE FROM InvoiceProduct_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Invoice_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Customer_156");
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (rs != null && !rs.isClosed()) 
				rs.close();
			if (ps!= null && !ps.isClosed())
				ps.close();
			if (con != null && !con.isClosed()) 
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 5. Method to add a customer record to the database with the provided data
	 * 
	 * @param customerCode
	 * @param customerType
	 * @param primaryContactPersonCode
	 * @param name
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */

	// Joe
	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,
			String name, String street, String city, String state, String zip, String country) {
		/* TODO */
		String url = DatabaseInfo.URL;
		String user = DatabaseInfo.USERNAME;
		String password = DatabaseInfo.PASSWORD;

		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query = "select personId from Person_156 where personCode like ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer personKey = null;

		try {
			ps = con.prepareStatement(query);
			ps.setString(1, primaryContactPersonCode);
			rs = ps.executeQuery();
			if (rs.next())
				personKey = rs.getInt("personId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (personKey == null)
			throw new RuntimeException("Please enter a valid personCode");
		
		query = "select customerId from Customer_156 where customerCode like ?;";

		ps = null;
		rs = null;
		Integer customerKey = null;

		try {
			ps = con.prepareStatement(query);
			ps.setString(1, customerCode);
			rs = ps.executeQuery();
			if (rs.next())
				customerKey = rs.getInt("customerId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (customerKey != null)
			throw new RuntimeException("Please enter a customerCode that does not already exist");
		

		String query1 = "SELECT personId FROM Person_156 WHERE personCode = ?";

		String query2 = "INSERT INTO Address_156 (street,city,state,zip,country)" + "VALUES(?,?,?,?,?)";

		String query3 = "INSERT INTO Customer_156 (customerCode,customerType,CustomerName,personId,addressId) "
				+ "VALUES (?,?,?,?,?)";

		try {
			pstmt1 = con.prepareStatement(query1);
			pstmt1.setString(1, primaryContactPersonCode);
			rs3 = pstmt1.executeQuery();

			pstmt2 = con.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
			pstmt2.setString(1, street);
			pstmt2.setString(2, city);
			pstmt2.setString(3, state);
			pstmt2.setString(4, zip);
			pstmt2.setString(5, country);

			pstmt2.executeUpdate();
			rs2 = pstmt2.getGeneratedKeys();
			rs2.next();

			int key = rs2.getInt(1);
			rs3.next();
			int key2 = rs3.getInt(1);
			pstmt3 = con.prepareStatement(query3);
			pstmt3.setString(1, customerCode);
			pstmt3.setString(2, customerType);
			pstmt3.setString(3, name);
			pstmt3.setInt(4, key2);
			pstmt3.setInt(5, key);
			pstmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (rs1 != null && !rs1.isClosed()) 
				rs1.close();
			if (rs2 != null && !rs2.isClosed()) 
				rs2.close();
			if (rs3 != null && !rs3.isClosed()) 
				rs3.close();
			if (rs != null && !rs.isClosed())
				rs.close();
			if (pstmt1 != null && !pstmt1.isClosed()) 
				pstmt1.close();
			if (pstmt2 != null && !pstmt2.isClosed())
				pstmt2.close();
			if (pstmt3 != null && !pstmt3.isClosed())
				pstmt3.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 6. Removes all product records from the database
	 */

	// Joe
	public static void removeAllProducts() {
		/* TODO */
		String url = DatabaseInfo.URL;
		String user = DatabaseInfo.USERNAME;
		String password = DatabaseInfo.PASSWORD;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement("DELETE FROM InvoiceProduct_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Concession_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Repair_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Rental_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Tow_156");
			ps.executeUpdate();
			ps = con.prepareStatement("DELETE FROM Product_156");
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (con != null && !con.isClosed()) 
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 7. Adds a concession record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param unitCost
	 */
	public static void addConcessionToInvoice(String productCode, String productLabel, double unitCost) {
		/* TODO */
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

		// Checks if the concession already exists
		String query = "SELECT concessionId FROM Concession_156 WHERE concessionCode LIKE ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer concessionCode = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next())
				concessionCode = rs.getInt("concessionId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (concessionCode != null)
			throw new RuntimeException("Please enter a productCode that doesn't already exist");

		// Inserts the new product into Product_156
		Integer productKey = null;
		query = "INSERT INTO Product_156 (productType, productDescription)" + "VALUES ('C', ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, productLabel);
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();
			productKey = rs.getInt(1);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Inserts the new person into Person_156
		query = "INSERT INTO Concession_156 (concessionCode, unitCost, productId)" + "VALUES (?, ?, ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, productCode);
			ps.setDouble(2, unitCost);
			ps.setInt(3, productKey);
			ps.executeUpdate();

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
	}

	/**
	 * 8. Adds a repair record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param proudctLabel
	 * @param partsCost
	 * @param laborRate
	 */
	public static void addRepair(String productCode, String productLabel, double partsCost, double laborRate) {
		/* TODO */
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

		// Checks if the concession already exists
		String query = "SELECT concessionId FROM Concession_156 WHERE concessionCode LIKE ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer concessionCode = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next())
				concessionCode = rs.getInt("concessionId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (concessionCode != null)
			throw new RuntimeException("Please enter a productCode that doesn't already exist");

		// Inserts the new product into Product_156
		Integer productKey = null;
		query = "INSERT INTO Product_156 (productType, productDescription)" + "VALUES ('R', ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, productLabel);
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			rs.next();
			productKey = rs.getInt(1);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Inserts the new person into Person_156
		query = "INSERT INTO Repair_156 (repairCode, partsRepairCost, hourlyRepairCost, productId)"
				+ "VALUES (?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, productCode);
			ps.setDouble(2, partsCost);
			ps.setDouble(3, laborRate);
			ps.setInt(4, productKey);
			ps.executeUpdate();

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
	}

	/**
	 * 9. Adds a towing record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param costPerMile
	 */

	// Joe
	public static void addTowing(String productCode, String productLabel, double costPerMile) {
		/* TODO */
		String url = DatabaseInfo.URL;
		String user = DatabaseInfo.USERNAME;
		String password = DatabaseInfo.PASSWORD;

		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query = "SELECT towId FROM Tow_156 WHERE towCode LIKE ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer towKey = null;

		try {
			ps = con.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next())
				towKey = rs.getInt("towId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (towKey != null)
			throw new RuntimeException("Please enter a productCode that doesn't already exist");

		String query1 = "INSERT INTO Product_156 (productType,productDescription) " + "VALUES ('T',?)";

		String query2 = "INSERT INTO Tow_156 (towCode,towCostPerMile,productId) " + "VALUES (?,?,?)";

		try {
			pstmt1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			pstmt1.setString(1, productLabel);
			pstmt1.executeUpdate();
			ResultSet keys = pstmt1.getGeneratedKeys();
			keys.next();
			int key = keys.getInt(1);
			pstmt2 = con.prepareStatement(query2);
			pstmt2.setString(1, productCode);
			pstmt2.setDouble(2, costPerMile);
			pstmt2.setInt(3, key);
			pstmt2.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			if (rs1 != null && !rs1.isClosed())
				rs1.close();
			if (rs2 != null && !rs2.isClosed())
				rs2.close();
			if (rs != null && !rs.isClosed())
				rs.close();
			if (pstmt1 != null && !pstmt1.isClosed())
				pstmt1.close();
			if (pstmt2 != null && !pstmt2.isClosed())
				pstmt2.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 10. Adds a rental record to the database with the provided data.
	 * 
	 * @param productCode
	 * @param productLabel
	 * @param dailyCost
	 * @param deposit
	 * @param cleaningFee
	 */

	// Joe
	public static void addRental(String productCode, String productLabel, double dailyCost, double deposit,
			double cleaningFee) {
		/* TODO */
		String url = DatabaseInfo.URL;
		String user = DatabaseInfo.USERNAME;
		String password = DatabaseInfo.PASSWORD;

		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String query = "SELECT rentalId FROM Rental_156 WHERE rentalCode LIKE ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer rentalKey = null;

		try {
			ps = con.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next())
				rentalKey = rs.getInt("rentalId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (rentalKey != null)
			throw new RuntimeException("Please enter a productCode that doesn't already exist");

		String query1 = "INSERT INTO Product_156 (productType,productDescription) " + "VALUES ('R',?)";

		String query2 = "INSERT INTO Rental_156 (rentalCode,rentalDailyCost,rentalDeposit,cleaningFee,productId) "
				+ "VALUES (?,?,?,?,?)";

		try {
			pstmt1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			pstmt1.setString(1, productLabel);
			pstmt1.executeUpdate();
			ResultSet keys = pstmt1.getGeneratedKeys();
			keys.next();
			int key = keys.getInt(1);
			pstmt2 = con.prepareStatement(query2);
			pstmt2.setString(1, productCode);
			pstmt2.setDouble(2, dailyCost);
			pstmt2.setDouble(3, deposit);
			pstmt2.setDouble(4, cleaningFee);
			pstmt2.setInt(5, key);
			pstmt2.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (rs1 != null && !rs1.isClosed())
				rs1.close();
			if (rs2 != null && !rs2.isClosed())
				rs2.close();
			if (rs != null && !rs.isClosed())
				rs.close();
			if (pstmt1 != null && !pstmt1.isClosed())
				pstmt1.close();
			if (pstmt2 != null && !pstmt2.isClosed())
				pstmt2.close();
			if (ps != null && !ps.isClosed())
				ps.close();
			if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 11. Removes all invoice records from the database
	 */

	// Joe
	public static void removeAllInvoices() {
		/* TODO */
		String url = DatabaseInfo.URL;
		String user = DatabaseInfo.USERNAME;
		String password = DatabaseInfo.PASSWORD;

		Connection con = null;
		Statement stmt = null;
		Statement stmtTwo = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			stmtTwo = con.createStatement();
			stmtTwo.executeUpdate("DELETE FROM InvoiceProduct_156");
			stmt.executeUpdate("DELETE FROM Invoice_156");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (rs != null && !rs.isClosed()) 
				rs.close();
			if (stmt != null && !stmt.isClosed())
				stmt.close();
			if (stmtTwo != null && !stmtTwo.isClosed())
				stmtTwo.close();
			if (con != null && !con.isClosed()) 
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 * 
	 * @param invoiceCode
	 * @param ownerCode
	 * @param customertCode
	 */

	// Talk about changing Invoice_156 table to incorporate the invoiceCode, and
	// split the products off into their own table

	public static void addInvoice(String invoiceCode, String ownerCode, String customerCode) {
		/* TODO */
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

		// Gets the personId associated with the personCode
		String query = "select invoiceId from Invoice_156 where invoiceCode like ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer invoiceKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next())
				invoiceKey = rs.getInt("personId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (invoiceKey != null)
			throw new RuntimeException("Please enter an invoice code that does not already exist");
		
		query = "select personId from Person_156 where personCode like ?;";

		ps = null;
		rs = null;
		Integer personKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, ownerCode);
			rs = ps.executeQuery();
			if (rs.next())
				personKey = rs.getInt("personId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (personKey == null)
			throw new RuntimeException("That owner does not exist");
		
		query = "select customerId from Customer_156 where customerCode like ?;";

		ps = null;
		rs = null;
		Integer customerKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, customerCode);
			rs = ps.executeQuery();
			if (rs.next())
				customerKey = rs.getInt("customerId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (customerKey == null)
			throw new RuntimeException("That customer does not exist");
		

		// Inserts the new person into Person_156
		query = "INSERT INTO Invoice_156 (invoiceCode, personId, customerId)" + "VALUES (?, ?, ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, invoiceCode);
			ps.setInt(2, personKey);
			ps.setInt(3, customerKey);
			ps.executeUpdate();
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
	}

	/**
	 * 13. Adds a particular Towing (corresponding to <code>productCode</code> to an
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of miles towed
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param milesTowed
	 */
	public static void addTowingToInvoice(String invoiceCode, String productCode, double milesTowed) {
		/* TODO */
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

		// Gets the personId associated with the personCode
		String query = "select invoiceId from Invoice_156 where invoiceCode like ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer invoiceKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next())
				invoiceKey = rs.getInt("invoiceId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (invoiceKey == null)
			throw new RuntimeException("Please enter an existing invoice code");
		
		query = "select productId from Tow_156 where towCode like ?;";

		ps = null;
		rs = null;
		Integer productKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next())
				productKey = rs.getInt("productId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (productKey == null)
			throw new RuntimeException("Please enter an existing product code");
		

		// Inserts the new product into InvoiceProduct_156
		query = "INSERT INTO InvoiceProduct_156 (invoiceId, productId, costMultiplier)" + "VALUES (?, ?, ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, invoiceKey);
			ps.setInt(2, productKey);
			ps.setDouble(3, milesTowed);
			ps.executeUpdate();
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
	}

	/**
	 * 14. Adds a particular Repair (corresponding to <code>productCode</code> to an
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of hours worked
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param hoursWorked
	 */
	public static void addRepairToInvoice(String invoiceCode, String productCode, double hoursWorked) {
		/* TODO */
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

		// Gets the personId associated with the personCode
		String query = "select invoiceId from Invoice_156 where invoiceCode like ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer invoiceKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next())
				invoiceKey = rs.getInt("invoiceId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (invoiceKey == null)
			throw new RuntimeException("Please enter an existing invoice code");
		
		query = "select productId from Repair_156 where repairCode like ?;";

		ps = null;
		rs = null;
		Integer productKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next())
				productKey = rs.getInt("productId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (productKey == null)
			throw new RuntimeException("Please enter an existing product code");
		

		// Inserts the new product into InvoiceProduct_156
		query = "INSERT INTO InvoiceProduct_156 (invoiceId, productId, costMultiplier)" + "VALUES (?, ?, ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, invoiceKey);
			ps.setInt(2, productKey);
			ps.setDouble(3, hoursWorked);
			ps.executeUpdate();
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
	}

	/**
	 * 15. Adds a particular Concession (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given number of quantity. NOTE: repairCode may be null
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param quantity
	 * @param repairCode
	 */

	public static void addConcession(String invoiceCode, String productCode, int quantity, String repairCode) {
		/* TODO */
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

		// Gets the personId associated with the personCode
		String query = "select invoiceId from Invoice_156 where invoiceCode like ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer invoiceKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next())
				invoiceKey = rs.getInt("invoiceId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (invoiceKey == null)
			throw new RuntimeException("Please enter an existing invoice code");
		
		query = "select productId from Concession_156 where concessionCode like ?;";

		ps = null;
		rs = null;
		Integer productKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next())
				productKey = rs.getInt("productId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (productKey == null)
			throw new RuntimeException("Please enter an existing product code");
		
		Integer repairKey = null;
		
		if (repairCode != null) {
			query = "select productId from Repair_156 where repairCode like ?;";

			ps = null;
			rs = null;

			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, productCode);
				rs = ps.executeQuery();
				if (rs.next())
					repairKey = rs.getInt("productId");
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (repairKey == null)
				throw new RuntimeException("That associated repair does not exist");
		}
		
		// Inserts the new product into InvoiceProduct_156
		boolean hasRepair = false;
		if (repairCode == null)
			query = "INSERT INTO InvoiceProduct_156 (invoiceId, productId, costMultiplier)" + "VALUES (?, ?, ?)";
		else {
			hasRepair = true;
			query = "INSERT INTO InvoiceProduct_156 (invoiceId, productId, costMultipler, associatedRepairCode)" + "VALUES (?, ?, ?, ?)";
		}

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, invoiceKey);
			ps.setInt(2, productKey);
			ps.setDouble(3, Double.valueOf(quantity));
			if (hasRepair) 
				ps.setString(4, repairCode);
			ps.executeUpdate();
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
	}

	/**
	 * 16. Adds a particular Rental (corresponding to <code>productCode</code> to an
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of days rented.
	 * 
	 * @param invoiceCode
	 * @param productCode
	 * @param daysRented
	 */

	public static void addRentalToInvoice(String invoiceCode, String productCode, double daysRented) {
		/* TODO */
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

		// Gets the personId associated with the personCode
		String query = "select invoiceId from Invoice_156 where invoiceCode like ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer invoiceKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			if (rs.next())
				invoiceKey = rs.getInt("invoiceId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (invoiceKey == null)
			throw new RuntimeException("Please enter an existing invoice code");
		
		query = "select productId from Rental_156 where rentalCode like ?;";

		ps = null;
		rs = null;
		Integer productKey = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			if (rs.next())
				productKey = rs.getInt("productId");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (productKey == null)
			throw new RuntimeException("Please enter an existing product code");
		

		// Inserts the new product into InvoiceProduct_156
		query = "INSERT INTO InvoiceProduct_156 (invoiceId, productId, costMultiplier)" + "VALUES (?, ?, ?)";

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, invoiceKey);
			ps.setInt(2, productKey);
			ps.setDouble(3, daysRented);
			ps.executeUpdate();
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
	}

}
