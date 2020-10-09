/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The list for invoices
 */

package com.bc;

import java.util.ArrayList;

public class InvoiceList implements ListInterface<Invoice>{
	private ArrayList<Invoice> listOfObjects = new ArrayList<Invoice>();

	public InvoiceList(ArrayList<String> inputList, PersonList people, CustomerList customers, ProductList products) {
		for (int i = 0; i < inputList.size(); i++) {
			String invoiceItem = inputList.get(i);
			String[] invoiceList = invoiceItem.split(";");

			// Creates an invoice and adds it to the InvoiceList
			this.listOfObjects.add(new Invoice(invoiceList[0], invoiceList[1], invoiceList[2], invoiceList[3], people, customers, products));
		}
	}
	
	public int getSize() {
		return this.listOfObjects.size();
	}
	
	public Invoice getObject(int index) {
		return this.listOfObjects.get(index);
	}

	public Invoice codeToObject(String code) {
		for(Invoice x : this.listOfObjects) {
			if(x.getInvoiceCode().equals(code))
				return x;
		}
		return null;
	}
}
