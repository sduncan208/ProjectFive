/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * CSCE 156
 * Main method that runs the program
 */

package com.bc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class InvoiceReport {
	public static ArrayList<String> fileReading(String Path) throws IOException {
		ReadingFiles fileOne = new ReadingFiles(Path);
		ArrayList<String> inputStuff = fileOne.getDataList();
		return inputStuff;
	}
	
	public static void output(InvoiceList invoices, String path) throws FileNotFoundException {
		SummaryWriter writer = new SummaryWriter(invoices, path);
		writer.printing();
	}

	public static void main(String[] args) throws IOException {
		String pathOne = "data/Persons.dat";
		String pathTwo = "data/Customers.dat";
		String pathThree = "data/Products.dat";
		String pathFour = "data/Invoices.dat";
		String pathFive = "data/output.txt";
		
		PersonList persons = new PersonList(fileReading(pathOne));
		CustomerList customers = new CustomerList(fileReading(pathTwo), persons);
		ProductList products = new ProductList(fileReading(pathThree));
		InvoiceList invoices = new InvoiceList(fileReading(pathFour), persons, customers, products);
		
		output(invoices, pathFive);
	}
}
