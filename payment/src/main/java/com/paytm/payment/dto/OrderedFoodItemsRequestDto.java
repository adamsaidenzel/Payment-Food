package com.paytm.payment.dto;

import java.util.List;

import com.paytm.payment.entities.IndianFoodEntity;


public class OrderedFoodItemsRequestDto {

	List<IndianFoodEntity> orderedFoodItems;

	public List<IndianFoodEntity> getOrderedFoodItems() {
		return orderedFoodItems;
	}

	public void setOrderedFoodItems(List<IndianFoodEntity> orderedFoodItems) {
		this.orderedFoodItems = orderedFoodItems;
	}
	
}
