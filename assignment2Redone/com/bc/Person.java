/* Sophia Duncan(37182093) & Joe Stollar(93721732) 
 *  
 * CSCE 156 
 */

package com.bc;

import java.util.ArrayList;

public class Person {
	private String personCode;
	private String name;
	private Address address;
	private ArrayList<String> emailAddresses = new ArrayList<>();

	public Person(String _personCode, String _name, String _address, ArrayList<String> inputAddresses) {
		this.personCode = _personCode;
		this.name = _name;

		String[] splitAddress = _address.split(",");
		this.address = new Address(splitAddress[0], splitAddress[1], splitAddress[2], splitAddress[3], splitAddress[4]);

		for (String addingAddress : inputAddresses) {
			this.emailAddresses.add(addingAddress);
		}
	}

	@Override
	public String toString() {
		return "Persons [personCode=" + this.personCode + ", name=" + this.name.toString() + ", address="
				+ this.address.toString() + ", emailAddress=" + getEmailAddresses() + "]";
	}

	public String getPersonCode() {
		return personCode;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getEmailAddresses() {
		String allEmails = "";
		for (String email : this.emailAddresses) {
			allEmails += email + " ";
		}
		return allEmails;
	}

}