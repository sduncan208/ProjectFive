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
		FileManager fileOne = new FileManager(Path);
		ArrayList<String> inputStuff = fileOne.getDataList();
		return inputStuff;
	}
	
	public static void output(AbstractedInvoiceList invoices, String path) throws FileNotFoundException {
		SummaryWriter writer = new SummaryWriter(invoices, path);
		writer.printing();
	}

	public static void main(String[] args) throws IOException {
		String pathFive = "data/output.txt";
		
		PersonList persons = new PersonList();
		CustomerList customers = new CustomerList(persons);
		ProductList products = new ProductList();
		InvoiceList invoices = new InvoiceList(persons, customers, products);
		AbstractedInvoiceList tester = new AbstractedInvoiceList();
		
		output(tester, pathFive);

	}
}
