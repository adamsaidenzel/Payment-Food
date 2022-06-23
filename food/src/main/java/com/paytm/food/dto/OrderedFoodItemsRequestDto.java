package com.paytm.food.dto;

import java.util.List;

import com.paytm.food.entities.IndianFoodEntity;

public class OrderedFoodItemsRequestDto {

	List<IndianFoodEntity> orderedFoodItems;

	public List<IndianFoodEntity> getOrderedFoodItems() {
		return orderedFoodItems;
	}

	public void setOrderedFoodItems(List<IndianFoodEntity> orderedFoodItems) {
		this.orderedFoodItems = orderedFoodItems;
	}
	
}
