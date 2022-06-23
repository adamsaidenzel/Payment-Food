package com.paytm.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytm.payment.dto.OrderedFoodItemsRequestDto;
import com.paytm.payment.dto.PriceDetailsResponseDto;
import com.paytm.payment.entities.IndianFoodEntity;

import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@PostMapping("/price-details")
	public PriceDetailsResponseDto getFoodPriceDetails(@RequestBody OrderedFoodItemsRequestDto orderedFoodItems) {
		PriceDetailsResponseDto response = new PriceDetailsResponseDto();
		// calculate totalPrice
		Double totalPrice = calculateTotalPrice(orderedFoodItems);
		// calculate taxAmount - 10 percent of totalPrice
		Double taxAmount = calculateTaxAmount(totalPrice);
		// calculate deliveryCharges - 2 percent of totalPrice
		Double deliveryCharges = calculateDeliveryCharges(totalPrice);
		// calculate paymentOptions - If the amount is more than 500 and if atleast one
		// orderfoodItem has coupounAvailable as true- Paytm,PhonePe
       // else give only credit card option
		List<String> paymentOptions = getPaymentOptions(totalPrice, orderedFoodItems);
		response.setAvailablePaymentOptions(paymentOptions);
		response.setDeliveryCharges(deliveryCharges);
		response.setTaxAmount(taxAmount);
		response.setTotalPrice(totalPrice);
		return response;
	}

	private List<String> getPaymentOptions(Double totalPrice, OrderedFoodItemsRequestDto orderedFoodItems) {
		List<String> availablePaymentOptions = new ArrayList<>();
		List<IndianFoodEntity> orderedFoods = orderedFoodItems.getOrderedFoodItems();
		Boolean isCoupounAvailable = false;
		for (IndianFoodEntity eachFoodItem : orderedFoods) {
			if (eachFoodItem.isCouponAvailable()) {
				isCoupounAvailable = true;
				break;
			}
		}
		if (!orderedFoodItems.equals(null)) {
			if (totalPrice > 500 && isCoupounAvailable) {
				availablePaymentOptions.add("phonePe");
				availablePaymentOptions.add("GooglePe");
				availablePaymentOptions.add("Paytme");
				availablePaymentOptions.add("Credit Card");
			} else {
				availablePaymentOptions.add("Credit Card");
			}
		} else {
			System.out.println("improper request sent");
		}

		return availablePaymentOptions;
	}

	private Double calculateDeliveryCharges(Double totalPrice) {
		Double deliveryCharges = (totalPrice * 2) / 100;
		return deliveryCharges;
	}

	private Double calculateTaxAmount(Double totalPrice) {
		Double taxAmount = (totalPrice * 10) / 100;
		return taxAmount;
	}

	private Double calculateTotalPrice(OrderedFoodItemsRequestDto orderedFoodItems) {
		Double totalPrice = 0.0;

		List<IndianFoodEntity> orderedFoods = orderedFoodItems.getOrderedFoodItems();
		for (IndianFoodEntity foodItem : orderedFoods) {
			totalPrice = totalPrice + foodItem.getPrice();
		}
		return totalPrice;
	}

}
