/* Sophia Duncan(37182093) & Joe Stollar(93721732) 
 *  
 * CSCE 156 
 */

package com.bc;
 
import java.util.ArrayList;
 
public class CustomerList {
 
	private ArrayList<Customer> listOfCustomers = new ArrayList<>();
 
	public CustomerList(ArrayList<String> inputList, PersonList Contact) {
		for (int i = 0; i < inputList.size(); i++) {
			String customerItem = inputList.get(i);
			String[] customerList = customerItem.split(";");
			String tempName = getContactFromCode(customerList[3], Contact).getName();
			this.listOfCustomers.add(new Customer(customerList[0], customerList[1], customerList[2], customerList[3], customerList[4], tempName));
		}
	}
 
	// Get the person assigned to each code
	public Person getContactFromCode(String personCode1, PersonList personCode2) {
		Person tempPerson = personCode2.getPersonFromCode(personCode1);
		return tempPerson;
	}
	
	public int getSize() {
		return listOfCustomers.size();
	}
 
	public Customer getCustomer(int index) {
		return listOfCustomers.get(index);
	}
}