/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The list for products
 */

package com.bc;

import java.util.ArrayList;

public class ProductList implements ListInterface<Products>{
	private ArrayList<Products> listOfObjects = new ArrayList<>();

	public ProductList(ArrayList<String> inputList) {
		for (int i = 0; i < inputList.size(); i++) {
			String productItem = inputList.get(i);
			String[] productList = productItem.split(";");

			if(productList[1].equals("R"))
				this.listOfObjects.add(new Rentals(productList[0], productList[1], productList[2], Double.parseDouble(productList[3]), Double.parseDouble(productList[4]), Double.parseDouble(productList[5])));
			else if(productList[1].equals("F"))
				this.listOfObjects.add(new Repairs(productList[0], productList[1], productList[2], Double.parseDouble(productList[3]), Double.parseDouble(productList[4])));
			else if(productList[1].equals("C"))
				this.listOfObjects.add(new Concessions(productList[0], productList[1], productList[2], Double.parseDouble(productList[3])));
			else if(productList[1].equals("T"))
				this.listOfObjects.add(new Towings(productList[0], productList[1], productList[2], Double.parseDouble(productList[3])));
		}
	}

	public Products codeToObject(String code) {
		for(Products x : this.listOfObjects) {
			if(x.getProductCode().equals(code))
				return x;
		}
		return null;
	}
	
	public int getSize() {
		return this.listOfObjects.size();
	}

	public Products getObject(int index) {
		return this.listOfObjects.get(index);
	}
}
