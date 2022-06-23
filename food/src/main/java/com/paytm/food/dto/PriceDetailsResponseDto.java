package com.paytm.food.dto;

import java.util.List;

public class PriceDetailsResponseDto {

	Double totalPrice;
	Double deliveryCharges;
	Double taxAmount;
	List<String> availablePaymentOptions;
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getDeliveryCharges() {
		return deliveryCharges;
	}
	public void setDeliveryCharges(Double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public List<String> getAvailablePaymentOptions() {
		return availablePaymentOptions;
	}
	public void setAvailablePaymentOptions(List<String> availablePaymentOptions) {
		this.availablePaymentOptions = availablePaymentOptions;
	}
	
	
}
