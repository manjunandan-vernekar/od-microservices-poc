package com.officedepot.domain.price;

public class PriceRow implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int price;
	private String accountNumber;
	private String currency;
	private int quantity;
	private String sku;
	private String unitOfMeasure;
	
	public PriceRow(int price, String currency, String accountNumber, int quantity, String sku, String unitOfMeasure)
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
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
