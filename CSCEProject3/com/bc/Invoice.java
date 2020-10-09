/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The general type for customers. Relates to persons, products, and customers
 */


package com.bc;

import java.util.ArrayList;

public class Invoice {
	private String invoiceCode;
	private String ownerCode;
	private String customerCode;
	private String listOfProducts;
	
	private Persons personAssociated;
	private Customers customerAssociated;
	private ArrayList<InvoiceProduct> productsAssociated;
	
	private CustomerList allCustomers;
	private PersonList allPeople;
	private ProductList allProducts;
	
	public Invoice(String _invoiceCode, String _ownerCode, String _customerCode, String _listOfProducts, PersonList _allPeople, CustomerList _allCustomers, ProductList _allProducts) {
		this.invoiceCode = _invoiceCode;
		this.ownerCode = _ownerCode;
		this.customerCode = _customerCode;
		this.listOfProducts = _listOfProducts;
		
		this.allCustomers = _allCustomers;
		this.allPeople = _allPeople;
		this.allProducts = _allProducts;
		this.productsAssociated = new ArrayList<InvoiceProduct>();
		
		retrievePerson();
		retrieveCustomer();
		createProductList(this.listOfProducts);
	}
	
	public void retrievePerson() {
		this.personAssociated = this.allPeople.codeToObject(this.ownerCode);
	}
	
	public void retrieveCustomer() {
		this.customerAssociated = this.allCustomers.codeToObject(this.customerCode);
	}
	
	//Splits the products list and returns the associated products
	public void createProductList(String productList) {
		//Splits up individual products
		String[] tempArray = productList.split(",");
		ArrayList<String[]> products = new ArrayList<String[]>();
		
		//Puts all the products into an arrayList as String[]
		for(String x : tempArray) {
			String[] productString = x.split(":");
			products.add(productString);
		}
		
		//Returns the product associated with each code and then adds that to the list of products 
		for(String[] x : products) {
			Products resultProduct = this.allProducts.codeToObject(x[0]);
			if (x.length == 2) {
				InvoiceProduct temp = new InvoiceProduct(resultProduct, Double.parseDouble(x[1]));
				this.productsAssociated.add(temp);
			} else {
				Products resultRepair = this.allProducts.codeToObject(x[2]);
				this.productsAssociated.add(new InvoiceProduct(resultProduct, Double.parseDouble(x[1]), resultRepair));
			}
		}
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public String getListOfProducts() {
		return listOfProducts;
	}

	public Persons getPersonAssociated() {
		return personAssociated;
	}

	public Customers getCustomerAssociated() {
		return customerAssociated;
	}

	public ArrayList<InvoiceProduct> getProductsAssociated() {
		return this.productsAssociated;
	}

	public CustomerList getAllCustomers() {
		return allCustomers;
	}

	public PersonList getAllPeople() {
		return allPeople;
	}

	public ProductList getAllProducts() {
		return allProducts;
	}
	
}
