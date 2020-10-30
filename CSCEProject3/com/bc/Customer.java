/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The general type for customers. Relates to persons
 */


package com.bc;

public class Customer {
	private String customerCode;
	private String customerType;
	private String name;
	private String customerName;
	private String contactCode;
	private Address address;

	public Customer(String customerCode, String customerType, String name, String contactCode, String _address, String customerName) {
		this.customerCode = customerCode;
		this.customerType = customerType;
		this.name = name;
		this.contactCode = contactCode;
		this.customerName = customerName;
		
		String[] splitAddress = _address.split(",");
		this.address = new Address(splitAddress[0], splitAddress[1], splitAddress[2], splitAddress[3], splitAddress[4]);
	}

	public String toString() {
		return "customer [customerCode=" + this.customerCode + ", name=" + this.name.toString() + ", address="
				+ this.address.toString() + "]";
	}

	public String getPersonCode() {
		return customerCode;
	}

	public void setPersonCode(String personCode) {
		this.customerCode = personCode;
	}

	public String[] getSplitName() {
		return getSplitName();
	}

	public void setSplitName(String[] _splitName) {
		// this.getSplitName() = _splitName;
	}

	public String[] getSplitAddress() {
		return getSplitAddress();
	}

	public String getAddingAddress() {
		return getAddingAddress();
	}

	public void setAddingAddress(String[] addingAddress) {
		// this.addingAddress = addingAddress;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public String getName() {
		return name;
	}

	public String getContactCode() {
		return contactCode;
	}

	public Address getAddress() {
		return address;
	}
}