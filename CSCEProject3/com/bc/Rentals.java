/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The rentals type of product
 */

package com.bc;

public class Rentals extends Products {
	private Double dailyCost;
	private Double deposit;
	private Double cleaningFee;
	
	public Rentals(String _productCode, String _productType, String _productLabel, Double _dailyCost, Double _deposit, Double _cleaningFee) {
		super(_productCode, _productType, _productLabel);
		this.dailyCost = _dailyCost;
		this.deposit = _deposit;
		this.cleaningFee = _cleaningFee;
	}

	public Double getDailyCost() {
		return dailyCost;
	}

	public Double getDeposit() {
		return deposit;
	}

	public Double getCleaningFee() {
		return cleaningFee;
	}

	public void setDailyCost(Double dailyCost) {
		this.dailyCost = dailyCost;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public void setCleaningFee(Double cleaningFee) {
		this.cleaningFee = cleaningFee;
	}
	
}
