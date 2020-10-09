/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The general type of product
 */


package com.bc;

public class Products {
	//All have this
	private String productCode;
	private String productType;
	private String productLabel;
	
	public Products(String _productCode, String _productType, String _productLabel) {
		this.productCode = _productCode;
		this.productType = _productType;
		this.productLabel = _productLabel;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getProductType() {
		return productType;
	}

	public String getProductLabel() {
		return productLabel;
	}
}
