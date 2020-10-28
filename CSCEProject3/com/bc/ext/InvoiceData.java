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
	public static void removeAllPersons() {
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

		// Gets the count of all Persons that exist
		String query = "select count(*) from Person_156";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer personCount = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			personCount = rs.getInt("count(*)");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		query = "select * from Person_156";

		ps = null;
		rs = null;
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			personCount = rs.getInt("count(*)");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		// Inserts the new address into Address_156
//		Integer addressKey = null;
//		query = "INSERT INTO Address_156 (street, city, state, zip, country)" + "VALUES (?, ?, ?, ?, ?)";
//
//		try {
//			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			ps.setString(1, street);
//			ps.setString(2, city);
//			ps.setString(3, state);
//			ps.setString(4, zip);
//			ps.setString(5, country);
//			ps.executeUpdate();
//
//			rs = ps.getGeneratedKeys();
//			rs.next();
//			addressKey = rs.getInt(1);
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		// Inserts the new person into Person_156
//		query = "INSERT INTO Person_156 (personCode, firstName, lastName, addressID)" + "VALUES (?, ?, ?, ?)";
//
//		try {
//			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			ps.setString(1, personCode);
//			ps.setString(2, firstName);
//			ps.setString(3, lastName);
//			ps.setInt(4, addressKey);
//			ps.executeUpdate();
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

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
	}

	/**
	 * 6. Removes all product records from the database
	 */

	// Joe
	public static void removeAllProducts() {
		/* TODO */
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
	}

	/**
	 * 11. Removes all invoice records from the database
	 */

	// Joe
	public static void removeAllInvoices() {
		/* TODO */
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

	// Joe
	public static void addConcession(String invoiceCode, String productCode, int quantity, String repairCode) {
		/* TODO */
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

	// Joe
	public static void addRentalToInvoice(String invoiceCode, String productCode, double daysRented) {
		/* TODO */
	}

}
