package com.bc;

public class Product {
	private String productCode;
	private String productType;
	private String productLabel;
	private Double dailyCost;
	private Double deposit;
	private Double cleaningFee;
	private Double partsCost;
	private Double hourlyLaborCost;
	private Double unitCost;
	private Double costPerMile;

	// For Rentals
	public Product(String _productCode, String _productType, String _productLabel, String _dailyCost, String _deposit,
			String _cleaningFee) {
		this.productCode = _productCode;
		this.productType = _productType;
		this.productLabel = _productLabel;
		this.dailyCost = Double.parseDouble(_dailyCost);
		this.deposit = Double.parseDouble(_deposit);
		this.cleaningFee = Double.parseDouble(_cleaningFee);
	}

	// For Repairs
	public Product(String _productCode, String _productType, String _productLabel, String _partsCost,
			String _hourlyLaborCost) {
		this.productCode = _productCode;
		this.productType = _productType;
		this.productLabel = _productLabel;
		this.partsCost = Double.parseDouble(_partsCost);
		this.hourlyLaborCost = Double.parseDouble(_hourlyLaborCost);
	}

	// For Concession
	public Product(String _productCode, String _productType, String _productLabel, String _unitCost) {
		this.productCode = _productCode;
		this.productType = _productType;
		this.productLabel = _productLabel;
		this.unitCost = Double.parseDouble(_unitCost);
	}

	// For Towing
	public Product(String _productCode, String _productType, String _productLabel, double _costPerMile) {
		this.productCode = _productCode;
		this.productType = _productType;
		this.productLabel = _productLabel;
		this.costPerMile = _costPerMile;
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

	public double getDailyCost() {
		return dailyCost;
	}

	public double getDeposit() {
		return deposit;
	}

	public double getCleaningFee() {
		return cleaningFee;
	}

	public double getPartsCost() {
		return partsCost;
	}

	public double getHourlyLaborCost() {
		return hourlyLaborCost;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public double getCostPerMile() {
		return costPerMile;
	}
}