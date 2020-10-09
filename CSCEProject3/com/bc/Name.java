/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * Contains names. It splits up a string with both first and last names
 */


package com.bc;

public class Name {
	private String firstName;
	private String lastName;
	
	public Name(String _firstName, String _lastName) {
		this.firstName = _firstName;
		this.lastName = _lastName;
	}

	
	@Override
	public String toString() {
		return new String(this.lastName + ", " + this.firstName);
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
