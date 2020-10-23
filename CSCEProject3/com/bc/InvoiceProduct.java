/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * Relates invoices to products so they remain together
 */

package com.bc;

import java.util.ArrayList;

public class InvoiceProduct {
	private Product product;
	private double num;
	private Product associatedRepair;
	
	public InvoiceProduct(Product _product, double d) {
		this.product = _product;
		this.num = d;
		this.associatedRepair = null;
	}
	
	public InvoiceProduct(Product _product, double _numOne, Product _repair) {
		this.product = _product;
		this.num = _numOne;
		this.associatedRepair = _repair;
	}
	
	public String getProductType() {
		return this.product.getProductType();
	}

	public Product getProduct() {
		return product;
	}
	
	public Product getAssociatedRepair() {
		return this.associatedRepair;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getNums() {
		return this.num;
	}
}
