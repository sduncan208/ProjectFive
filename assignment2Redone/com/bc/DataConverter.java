/* Sophia Duncan(37182093) & Joe Stollar(93721732) 
 *  
 * CSCE 156 
 */

package com.bc;

import java.io.IOException;
import java.util.ArrayList;

public class DataConverter {

	public static void main(String[] args) throws IOException {
		// Change pathway to data/Person
		String pathOne = "data/Persons.dat";
		String pathTwo = "data/Customers.dat";
		String pathThree = "data/Products.dat";

		// Reads the file for the persons
		FileManager fileOne = new FileManager(pathOne);
		ArrayList<String> allPersons = fileOne.getDataList();
		PersonList testOne = new PersonList(allPersons);

		// Reads the file for the customers
		FileManager fileTwo = new FileManager(pathTwo);
		ArrayList<String> allCustomers = fileTwo.getDataList();
		CustomerList testTwo = new CustomerList(allCustomers, testOne);

		// Reads the file for the products
		FileManager fileThree = new FileManager(pathThree);
		ArrayList<String> allProducts = fileThree.getDataList();
		ProductList testThree = new ProductList(allProducts);
		
		//Writes to files
		Writer<PersonList> personFile = new Writer<PersonList>(testOne);
		Writer<CustomerList> customerFile = new Writer<CustomerList>(testTwo);
		Writer<ProductList> productFile = new Writer<ProductList>(testThree);
		personFile.writeJson("data/Persons.json");
		customerFile.writeJson("data/Customers.json");		
		productFile.writeJson("data/Products.json");
		personFile.writeXml("data/Persons.xml");
		customerFile.writeXml("data/Customers.xml");		
		productFile.writeXml("data/Products.xml");
	}

}