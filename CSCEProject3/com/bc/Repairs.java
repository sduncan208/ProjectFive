/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The repairs type of product
 */

package com.bc;

public class Repairs extends Products {
	private Double partsCost;
	private Double hourlyLaborCost;

	public Repairs(String _productCode, String _productType, String _productLabel, Double _partsCost, Double _hourlyLaborCost) {
		super(_productCode, _productType, _productLabel);
		this.partsCost = _partsCost;
		this.hourlyLaborCost = _hourlyLaborCost;
	}

	public Double getPartsCost() {
		return partsCost;
	}

	public Double getHourlyLaborCost() {
		return hourlyLaborCost;
	}

	public void setPartsCost(Double partsCost) {
		this.partsCost = partsCost;
	}

	public void setHourlyLaborCost(Double hourlyLaborCost) {
		this.hourlyLaborCost = hourlyLaborCost;
	}
}
