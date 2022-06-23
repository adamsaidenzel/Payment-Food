package com.paytm.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.paytm.food.dto.OrderedFoodItemsRequestDto;
import com.paytm.food.dto.PriceDetailsResponseDto;
import com.paytm.food.entities.IndianFoodEntity;
import com.paytm.food.service.IndianFoodService;


@RestController
@RequestMapping("/indian-food")
public class IndianFoodController {
	
	@Autowired
	IndianFoodService indianFoodSvc;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/welcome-message")
	public String getWelcomeMessage() {
		String message = "Welcome to Indian food";
		return message;
	}

	@PostMapping("/create-food-item")
	public void createFoodItem(@RequestBody IndianFoodEntity indianFoodItem) {
		indianFoodSvc.createFoodItem(indianFoodItem);
	}
	
	@GetMapping("/fetch-food-items")
	public List<IndianFoodEntity> getFoodItems() {
		return indianFoodSvc.getFoodItems();
	}
	
	
	@PutMapping("/update-food-item")
	public void updateFoodItem(@RequestBody IndianFoodEntity indianFoodItem) {
		indianFoodSvc.updateFoodItem(indianFoodItem);
	}
	
	@PatchMapping("/update-rating/{id}/{rating}")
	public void updateRating(@PathVariable Long id,@PathVariable Integer rating) {
		indianFoodSvc.updateRating(id,rating);
	}
	
	@DeleteMapping("/delete-food-item")
	public void deleteFoodItem( @RequestBody IndianFoodEntity indianFoodEntity) {
		indianFoodSvc.deleteFoodItem(indianFoodEntity);
	}

	@DeleteMapping("/delete-food-item/{id}")
	public void deleteFoodItemById(@PathVariable Long id) {
		indianFoodSvc.deleteFoodItemById(id);
	}
	
	/*
	 * @GetMapping("/price-details") public Integer getPriceDetails() { String url =
	 * "http://localhost:8083/payment/total-cost"; HttpHeaders headers = new
	 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON); HttpEntity
	 * entity = new HttpEntity(headers); Integer priceDetails =
	 * restTemplate.exchange(url, HttpMethod.GET,entity , Integer.class).getBody();
	 * return priceDetails; }
	 */
	
	@PostMapping("/ordered-price-details")
	public PriceDetailsResponseDto getFoodPriceDetails(@RequestBody OrderedFoodItemsRequestDto orderedFoodItems) {
		String url = "http://localhost:8085/payment/price-details";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PriceDetailsResponseDto> entity = new HttpEntity(orderedFoodItems,headers);
		PriceDetailsResponseDto priceDetails = restTemplate.exchange(url, HttpMethod.POST,entity , PriceDetailsResponseDto.class).getBody();
		return priceDetails;
	}
	
}