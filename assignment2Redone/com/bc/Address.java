package com.bc;

public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;

	public Address(String _street, String _city, String _state, String _zip, String _country) {
		this.street = _street;
		this.city = _city;
		this.state = _state;
		this.zip = _zip;
		this.country = _country;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", country="
				+ country + "]";
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}


	public String getZip() {
		return zip;
	}


	public String getCountry() {
		return country;
	}
}