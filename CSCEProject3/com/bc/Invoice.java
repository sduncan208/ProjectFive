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
	
	private Person personAssociated;
	private Customer customerAssociated;
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
			Product resultProduct = this.allProducts.codeToObject(x[0]);
			if (x.length == 2) {
				InvoiceProduct temp = new InvoiceProduct(resultProduct, Double.parseDouble(x[1]));
				this.productsAssociated.add(temp);
			} else {
				Product resultRepair = this.allProducts.codeToObject(x[2]);
				this.productsAssociated.add(new InvoiceProduct(resultProduct, Double.parseDouble(x[1]), resultRepair));
			}
		}
	}

	
	//Calculations
	public double individualSubtotal(int productIndex) {
		double total = 0;
		InvoiceProduct temp = getProductsAssociated().get(productIndex);
		// Rentals
		if (temp.getProduct().getProductType().equals("R")) {
			Rental tempProduct = (Rental) temp.getProduct();
			total += tempProduct.getDailyCost() * temp.getNums();
			total += tempProduct.getCleaningFee();
			total -= tempProduct.getDeposit();
		}
		// Repairs
		if (temp.getProduct().getProductType().equals("F")) {
			Repair tempProduct = (Repair) temp.getProduct();
			total += tempProduct.getHourlyLaborCost() * temp.getNums();
			total += tempProduct.getPartsCost();
		}
		// Concessions
		if (temp.getProduct().getProductType().equals("C")) {
			Concession tempProduct = (Concession) temp.getProduct();
			total += tempProduct.getUnitCost() * temp.getNums();
		}
		// Towings
		if (temp.getProduct().getProductType().equals("T")) {
			Towing tempProduct = (Towing) temp.getProduct();
			total += tempProduct.getCostPerMile() * temp.getNums();
		}
		return total;
	}

	public double individualDiscount(int productIndex) {
		double total = 0;
		InvoiceProduct temp = getProductsAssociated().get(productIndex);

		// Checks if the towing is free. Only runs for a towing
		if (temp.getProductType().equals("T")) {
			boolean hasTowing = false;
			boolean hasRepair = false;
			boolean hasRental = false;
			for (int i = 0; i < getProductsAssociated().size(); i++) {
				if (getProductsAssociated().get(i).getProduct().getProductType().equals("R"))
					hasRental = true;
				if (getProductsAssociated().get(i).getProduct().getProductType().equals("F"))
					hasRepair = true;
				if (getProductsAssociated().get(i).getProduct().getProductType().equals("T"))
					hasTowing = true;
			}

			for (int i = 0; i < getProductsAssociated().size(); i++) {
				if (hasTowing && hasRepair && hasRental
						&& getProductsAssociated().get(i).getProduct().getProductType().equals("T")) {
					Towing tempTowing = (Towing) getProductsAssociated().get(i).getProduct();
					total -= tempTowing.getCostPerMile() * getProductsAssociated().get(i).getNums();
				}
			}
		}

		// Checks for an associated repair in the concession. Only runs for a concession
		if (temp.getProductType().equals("C")) {
			for (int i = 0; i < getProductsAssociated().size(); i++) {
				if (getProductsAssociated().get(i).getProduct().getProductType().equals("C")) {
					if (getProductsAssociated().get(i).getAssociatedRepair() != null) {
						Concession tempConcessions = (Concession) getProductsAssociated().get(i)
								.getProduct();
						total -= 0.1 * (tempConcessions.getUnitCost()
								* getProductsAssociated().get(i).getNums());
					}
				}
			}
		}
		
		return total;
	}

	public double individualTaxes(int productIndex) {
		double total = 0;
		if (getCustomerAssociated().getCustomerType().equals("B"))
			total = 0.0425 * (individualSubtotal(productIndex) + individualDiscount(productIndex));
		else 
			total = 0.08 * (individualSubtotal(productIndex) + individualDiscount(productIndex));
		return total;
	}

	public double individualTotal(int productIndex) {
		return individualSubtotal(productIndex) + individualDiscount(productIndex) + individualTaxes(productIndex);
	}
	
	public double subtotal() {
		double total = 0;
		for (int i = 0; i < getProductsAssociated().size(); i++) {
			total += individualSubtotal(i);
		}
		return total;
	}
	
	public double discounts() {
		return beforeDiscounts() + loyalCustomer();
	}
	
	public double beforeDiscounts() {
		double total = 0;
		
		for(int i = 0; i < getProductsAssociated().size(); i++) {
			total += individualDiscount(i);
		}
		
		return total;
	}

	public double loyalCustomer() {
		double total = 0;
		// Checks for a personal account with 2 emails
		if (getCustomerAssociated().getCustomerType().equals("P")) {
			String tempCode = getCustomerAssociated().getContactCode();
			if (getAllPeople().codeToObject(tempCode).getEmailArray().size() >= 2) {
				total -= (subtotal() + taxes()) * 0.05;
			}
		}
		
		return total;
	}
	
	public double fees() {
		double total = 0;
		// Checks for a business account
		if (getCustomerAssociated().getCustomerType().equals("B"))
			total += 75.50;
		return total;
	}
	
	public double taxes() {
		double total = 0;
		// Checks for a business account
		if (getCustomerAssociated().getCustomerType().equals("B"))
			total = 0.0425 * (subtotal() + beforeDiscounts());
		else 
			total = 0.08 * (subtotal() + beforeDiscounts());
		return total;
	}
	
	public double total() {
		return subtotal() + discounts() + fees() + taxes();
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

	public Person getPersonAssociated() {
		return personAssociated;
	}

	public Customer getCustomerAssociated() {
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
