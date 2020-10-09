/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The list for customers
 */

package com.bc;

import java.util.ArrayList;

public class CustomerList implements ListInterface<Customers>{

	private ArrayList<Customers> listOfObjects = new ArrayList<>();

	public CustomerList(ArrayList<String> inputList, PersonList Contact) {
		ArrayList<String> tempArray = new ArrayList<>();

		for (int i = 0; i < inputList.size(); i++) {
			String customerItem = inputList.get(i);
			String[] customerList = customerItem.split(";");
			Name tempName = getContactFromCode(customerList[3], Contact).getName();
			this.listOfObjects.add(new Customers(customerList[0], customerList[1], customerList[2], customerList[3], customerList[4], tempName));
			tempArray.add(customerList[3]);
		}
	}
	
	//Get the customer from each code
	public Customers codeToObject (String code) {
		for (int i = 0; i < this.listOfObjects.size(); i++) {
			if (getObject(i).getCustomerCode().equals(code))
				return getObject(i);
		}
		return null;
	}

	// Get the person assigned to each code
	public Persons getContactFromCode(String personCode1, PersonList personCode2) {
		Persons tempPerson = personCode2.codeToObject(personCode1);
		return tempPerson;
	}
	
	public int getSize() {
		return listOfObjects.size();
	}

	public Customers getObject(int index) {
		return listOfObjects.get(index);
	}
}