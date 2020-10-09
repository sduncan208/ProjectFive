/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The general type for people
 */

package com.bc;

import java.util.ArrayList;

public class Persons extends AddressName {
	private String personCode;
	private ArrayList<String> emailAddresses = new ArrayList<>();

	public Persons(String _personCode, String _name, String _address, ArrayList<String> inputAddresses) {
		super(_name, _address);
		this.personCode = _personCode;
		for(String addingAddress : inputAddresses) {
			this.emailAddresses.add(addingAddress);
		}
	}
	
	public Persons(String _personCode, String _name, String _address) {
		super(_name, _address);
		this.personCode = _personCode;
		this.emailAddresses.add("");
	}
	
	public Address getAddress() {
		return super.personAddress;
	}
	
	public Name getName() {
		return super.personName;
	}
	
	public String nameToString() {
		return super.personName.toString();
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
		return "Persons [personCode=" + this.personCode + ", name=" + super.personName.toString() + ", address=" + super.personAddress.toString() + ", emailAddress="
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
