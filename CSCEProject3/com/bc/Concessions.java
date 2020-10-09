/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The concessions type of product
 */

package com.bc;

public class Concessions extends Products {
	private Double unitCost;
	
	public Concessions(String _productCode, String _productType, String _productLabel, Double _unitCost) {
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
