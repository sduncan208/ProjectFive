/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The list for products
 */

package com.bc;

import java.util.ArrayList;

public class ProductList implements ListInterface<Product>{
	private ArrayList<Product> listOfObjects = new ArrayList<>();

	public ProductList(ArrayList<String> inputList) {
		for (int i = 0; i < inputList.size(); i++) {
			String productItem = inputList.get(i);
			String[] productList = productItem.split(";");

			if(productList[1].equals("R"))
				this.listOfObjects.add(new Rental(productList[0], productList[1], productList[2], Double.parseDouble(productList[3]), Double.parseDouble(productList[4]), Double.parseDouble(productList[5])));
			else if(productList[1].equals("F"))
				this.listOfObjects.add(new Repair(productList[0], productList[1], productList[2], Double.parseDouble(productList[3]), Double.parseDouble(productList[4])));
			else if(productList[1].equals("C"))
				this.listOfObjects.add(new Concession(productList[0], productList[1], productList[2], Double.parseDouble(productList[3])));
			else if(productList[1].equals("T"))
				this.listOfObjects.add(new Towing(productList[0], productList[1], productList[2], Double.parseDouble(productList[3])));
		}
	}

	public Product codeToObject(String code) {
		for(Product x : this.listOfObjects) {
			if(x.getProductCode().equals(code))
				return x;
		}
		return null;
	}
	
	public int getSize() {
		return this.listOfObjects.size();
	}

	public Product getObject(int index) {
		return this.listOfObjects.get(index);
	}
}
