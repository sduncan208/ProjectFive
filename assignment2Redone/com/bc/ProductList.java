package com.bc;

import java.util.ArrayList;

public class ProductList {
	private ArrayList<Product> listOfProducts = new ArrayList<>();

	public ProductList(ArrayList<String> inputList) {
		for (int i = 0; i < inputList.size(); i++) {
			String productItem = inputList.get(i);
			String[] productList = productItem.split(";");

			if (productList[1].equals("R"))
				this.listOfProducts.add(new Product(productList[0], productList[1], productList[2], productList[3],
						productList[4], productList[5]));
			else if (productList[1].equals("F"))
				this.listOfProducts.add(
						new Product(productList[0], productList[1], productList[2], productList[3], productList[4]));
			else if (productList[1].equals("C"))
				this.listOfProducts.add(new Product(productList[0], productList[1], productList[2], productList[3]));
			else if (productList[1].equals("T"))
				this.listOfProducts.add(new Product(productList[0], productList[1], productList[2],
						Double.parseDouble(productList[3])));
		}
	}

	public int getSize() {
		return this.listOfProducts.size();
	}

	public Product getProduct(int index) {
		return this.listOfProducts.get(index);
	}
}