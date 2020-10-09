/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * Contains street, city, stat, zipcode, and country
 */

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

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
