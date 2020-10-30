/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The list for persons
 */

package com.bc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bc.ext.DatabaseInfo;

public class PersonList implements ListInterface<Person> {
	private ArrayList<Person> listOfObjects = new ArrayList<>();

	public PersonList() {
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
		String query = "select * from Person_156 as a left join Address_156 as b on a.addressId = b.addressId;";

		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				this.listOfObjects.add(new Person(rs.getString("personCode"), rs.getString("firstName"), rs.getString("lastName")));
				this.listOfObjects.get(count).addAddress(new Address(rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("state"), rs.getString("country")));
				count++;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		query = "select emailAddress from Person_156 as a join Email_156 as b on a.personId = b.personId where a.personCode = ?;";

		ps = null;
		rs = null;

		for (int i = 0; i < this.listOfObjects.size(); i++) {
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, listOfObjects.get(i).getPersonCode());
				rs = ps.executeQuery();
				while (rs.next()) {
					listOfObjects.get(i).addEmailAddress(rs.getString("emailAddress"));
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

	public Person codeToObject(String personCode) {
		for (int i = 0; i < this.listOfObjects.size(); i++) {
			if (getObject(i).getPersonCode().equals(personCode))
				return getObject(i);
		}
		return null;
	}

	public int getSize() {
		return listOfObjects.size();
	}

	public Person getObject(int index) {
		return listOfObjects.get(index);
	}
}
