/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * Combines address and name
 */

package com.bc;

public class AddressName {
	protected Name personName;
	protected Address personAddress;
	
	public AddressName(String _name, String _address) {
		String[] splitName = _name.split(",");
		this.personName = new Name(splitName[1], splitName[0]);	
		
		String[] splitAddress = _address.split(",");
		this.personAddress = new Address(splitAddress[0], splitAddress[1], splitAddress[2], splitAddress[3], splitAddress[4]);
	}

	public Name getPersonName() {
		return personName;
	}

	public Address getPersonAddress() {
		return personAddress;
	}
}
