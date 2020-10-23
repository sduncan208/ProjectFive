/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The concessions type of product
 */

package com.bc;

public class Concession extends Product {
	private Double unitCost;
	
	public Concession(String _productCode, String _productType, String _productLabel, Double _unitCost) {
		super(_productCode, _productType, _productLabel);
		this.unitCost = _unitCost;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
}
