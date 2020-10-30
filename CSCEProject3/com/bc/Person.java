/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The general type for people
 */

package com.bc;

import java.util.ArrayList;

public class Person {
	private String personCode;
	private String firstName;
	private String lastName;
	private Address personAddress;
	private ArrayList<String> emailAddresses = new ArrayList<>();

	public Person(String _personCode, String _firstName, String _lastName) {
		this.firstName = _firstName;
		this.lastName = _lastName;
		this.personCode = _personCode;
		this.personAddress = null;
		this.emailAddresses.add("");
	}
	
	public void addEmailAddress(String emailAddress) {
		if(this.emailAddresses.get(0).equals(""))
			this.emailAddresses.set(0, emailAddress);
		else 
			this.emailAddresses.add(emailAddress);
	}
	
	public void addAddress(Address address) {
		this.personAddress = address;
	}
	
	public Address getAddress() {
		return this.personAddress;
	}
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	public String emailsToString() {
		String returnString = "[";
		if(this.emailAddresses.size() > 0) {
			for(int i = 0; i < this.emailAddresses.size(); i++) {
				returnString += this.emailAddresses.get(i);
				if(i != this.emailAddresses.size() - 1)
					returnString += ", ";
			}
			returnString += "]";
			return returnString;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Persons [personCode=" + this.personCode + ", name=" + this.getName() + ", address=" + this.personAddress.toString() + ", emailAddress="
				+ getEmailAddresses() + "]";
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getEmailAddresses() {
		String allEmails = "";
		for(String email : this.emailAddresses) {
			allEmails += email + " ";
		}
		return allEmails;
	}
	
	public ArrayList<String> getEmailArray() {
		return this.emailAddresses;
	}

}
