package com.bc;
 
public class Customer {
	private String customerCode;
	private String customerType;
	private String name;
	private String customerName;
	private String contactCode;
	private Address address;
 
	public Customer(String customerCode, String customerType, String name, String contactCode, String _address, String _customerName) {
		this.customerCode = customerCode;
		this.customerType = customerType;
		this.name = name;
		this.contactCode = contactCode;
		this.customerName = _customerName;
		
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
 
	public String getCustomerName() {
		return this.customerName;
	}
 
	public String[] getSplitAddress() {
		return getSplitAddress();
	}
 
	public String getAddingAddress() {
		return getAddingAddress();
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