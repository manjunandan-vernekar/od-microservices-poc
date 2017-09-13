package com.officedepot.domain.price;

public class PriceRow implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String price;
	private String accountNumber;
	private String currency;
	private String quantity;
	private String sku;
	private String unitOfMeasure;
	
	PriceRow() {
		System.out.println("Creating price row");
		this.price = "10";
	}
	
	public PriceRow(String price, String currency, String accountNumber, String quantity, String sku, String unitOfMeasure)
	{
		this.price = price;
		this.currency = currency;
		this.accountNumber = accountNumber;
		this.quantity = quantity;
		this.sku = sku;
		this.unitOfMeasure = unitOfMeasure;
	}
	
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
