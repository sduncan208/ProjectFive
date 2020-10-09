/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The towings type of product
 */

package com.bc;

public class Towings extends Products{
	private Double costPerMile;

	public Towings(String _productCode, String _productType, String _productLabel, Double _costPerMile) {
		super(_productCode, _productType, _productLabel);
		this.costPerMile = _costPerMile;
	}
	
	public Double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(Double costPerMile) {
		this.costPerMile = costPerMile;
	}
}
