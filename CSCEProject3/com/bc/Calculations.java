/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * Makes all calculations for the BumprCars
 */

package com.bc;

public class Calculations {
	private Invoice invoice;
	
	public Calculations(Invoice _invoice) {
		this.invoice = _invoice;
	}
	
	public double individualSubtotal(int productIndex) {
		double total = 0;
		InvoiceProduct temp = this.invoice.getProductsAssociated().get(productIndex);
		// Rentals
		if (temp.getProduct().getProductType().equals("R")) {
			Rentals tempProduct = (Rentals) temp.getProduct();
			total += tempProduct.getDailyCost() * temp.getNums();
			total += tempProduct.getCleaningFee();
			total -= tempProduct.getDeposit();
		}
		// Repairs
		if (temp.getProduct().getProductType().equals("F")) {
			Repairs tempProduct = (Repairs) temp.getProduct();
			total += tempProduct.getHourlyLaborCost() * temp.getNums();
			total += tempProduct.getPartsCost();
		}
		// Concessions
		if (temp.getProduct().getProductType().equals("C")) {
			Concessions tempProduct = (Concessions) temp.getProduct();
			total += tempProduct.getUnitCost() * temp.getNums();
		}
		// Towings
		if (temp.getProduct().getProductType().equals("T")) {
			Towings tempProduct = (Towings) temp.getProduct();
			total += tempProduct.getCostPerMile() * temp.getNums();
		}
		return total;
	}

	public double individualDiscount(int productIndex) {
		double total = 0;
		InvoiceProduct temp = this.invoice.getProductsAssociated().get(productIndex);

		// Checks if the towing is free. Only runs for a towing
		if (temp.getProductType().equals("T")) {
			boolean hasTowing = false;
			boolean hasRepair = false;
			boolean hasRental = false;
			for (int i = 0; i < this.invoice.getProductsAssociated().size(); i++) {
				if (this.invoice.getProductsAssociated().get(i).getProduct().getProductType().equals("R"))
					hasRental = true;
				if (this.invoice.getProductsAssociated().get(i).getProduct().getProductType().equals("F"))
					hasRepair = true;
				if (this.invoice.getProductsAssociated().get(i).getProduct().getProductType().equals("T"))
					hasTowing = true;
			}

			for (int i = 0; i < this.invoice.getProductsAssociated().size(); i++) {
				if (hasTowing && hasRepair && hasRental
						&& this.invoice.getProductsAssociated().get(i).getProduct().getProductType().equals("T")) {
					Towings tempTowing = (Towings) this.invoice.getProductsAssociated().get(i).getProduct();
					total -= tempTowing.getCostPerMile() * this.invoice.getProductsAssociated().get(i).getNums();
				}
			}
		}

		// Checks for an associated repair in the concession. Only runs for a concession
		if (temp.getProductType().equals("C")) {
			for (int i = 0; i < this.invoice.getProductsAssociated().size(); i++) {
				if (this.invoice.getProductsAssociated().get(i).getProduct().getProductType().equals("C")) {
					if (this.invoice.getProductsAssociated().get(i).getAssociatedRepair() != null) {
						Concessions tempConcessions = (Concessions) this.invoice.getProductsAssociated().get(i)
								.getProduct();
						total -= 0.1 * (tempConcessions.getUnitCost()
								* this.invoice.getProductsAssociated().get(i).getNums());
					}
				}
			}
		}
		
		return total;
	}

	public double individualTaxes(int productIndex) {
		double total = 0;
		if (this.invoice.getCustomerAssociated().getCustomerType().equals("B"))
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
		for (int i = 0; i < this.invoice.getProductsAssociated().size(); i++) {
			total += individualSubtotal(i);
		}
		return total;
	}
	
	public double discounts() {
		return beforeDiscounts() + loyalCustomer();
	}
	
	public double beforeDiscounts() {
		double total = 0;
		
		for(int i = 0; i < this.invoice.getProductsAssociated().size(); i++) {
			total += individualDiscount(i);
		}
		
		return total;
	}

	public double loyalCustomer() {
		double total = 0;
		// Checks for a personal account with 2 emails
		if (this.invoice.getCustomerAssociated().getCustomerType().equals("P")) {
			String tempCode = this.invoice.getCustomerAssociated().getContactCode();
			if (this.invoice.getAllPeople().codeToObject(tempCode).getEmailArray().size() >= 2) {
				total -= (subtotal() + taxes()) * 0.05;
			}
		}
		
		return total;
	}
	
	public double fees() {
		double total = 0;
		// Checks for a business account
		if (this.invoice.getCustomerAssociated().getCustomerType().equals("B"))
			total += 75.50;
		return total;
	}
	
	public double taxes() {
		double total = 0;
		// Checks for a business account
		if (this.invoice.getCustomerAssociated().getCustomerType().equals("B"))
			total = 0.0425 * (subtotal() + beforeDiscounts());
		else 
			total = 0.08 * (subtotal() + beforeDiscounts());
		return total;
	}
	
	public double total() {
		return subtotal() + discounts() + fees() + taxes();
	}
}
