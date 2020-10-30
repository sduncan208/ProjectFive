/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The list for products
 */

package com.bc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bc.ext.DatabaseInfo;

public class ProductList implements ListInterface<Product>{
	private ArrayList<Product> listOfObjects = new ArrayList<>();

	public ProductList() {
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

		String rentalQuery = "select * from Product_156 as a join Rental_156 as b on a.productId = b.productId;";
		String repairQuery = "select * from Product_156 as a join Repair_156 as b on a.productId = b.productId;";
		String towQuery = "select * from Product_156 as a join Tow_156 as b on a.productId = b.productId;";
		String concessionQuery = "select * from Product_156 as a join Concession_156 as b on a.productId = b.productId;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(rentalQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				this.listOfObjects.add(new Rental(rs.getString("rentalCode"), rs.getString("productType"), rs.getString("productDescription"), rs.getDouble("rentalDailyCost"), rs.getDouble("rentalDeposit"), rs.getDouble("cleaningFee")));
			}
			
			ps = conn.prepareStatement(repairQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				this.listOfObjects.add(new Repair(rs.getString("repairCode"), rs.getString("productType"), rs.getString("productDescription"), rs.getDouble("partsRepairCost"), rs.getDouble("hourlyRepairCost")));
			}
			
			ps = conn.prepareStatement(towQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				this.listOfObjects.add(new Towing(rs.getString("towCode"), rs.getString("productType"), rs.getString("productDescription"), rs.getDouble("towCostPerMile")));
			}
			
			ps = conn.prepareStatement(concessionQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				this.listOfObjects.add(new Concession(rs.getString("concessionCode"), rs.getString("productType"), rs.getString("productDescription"), rs.getDouble("unitCost")));
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
	}

	public Product codeToObject(String code) {
		for(Product x : this.listOfObjects) {
			if(x.getProductCode().equals(code))
				return x;
		}
		return null;
	}
	
	public int getSize() {
		return this.listOfObjects.size();
	}

	public Product getObject(int index) {
		return this.listOfObjects.get(index);
	}
}
