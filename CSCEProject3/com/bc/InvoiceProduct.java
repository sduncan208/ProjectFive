/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * Relates invoices to products so they remain together
 */

package com.bc;

import java.util.ArrayList;

public class InvoiceProduct {
	private Products product;
	private double num;
	private Products associatedRepair;
	
	public InvoiceProduct(Products _product, double d) {
		this.product = _product;
		this.num = d;
		this.associatedRepair = null;
	}
	
	public InvoiceProduct(Products _product, double _numOne, Products _repair) {
		this.product = _product;
		this.num = _numOne;
		this.associatedRepair = _repair;
	}
	
	public String getProductType() {
		return this.product.getProductType();
	}

	public Products getProduct() {
		return product;
	}
	
	public Products getAssociatedRepair() {
		return this.associatedRepair;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public double getNums() {
		return this.num;
	}
}
