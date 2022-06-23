package com.paytm.food.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paytm.food.entities.IndianFoodEntity;
import  com.paytm.food.repository.IndianFoodRepository;

@Service
public class IndianFoodService {

	@Autowired
	IndianFoodRepository indianFoodRepo;
	
	public void createFoodItem(IndianFoodEntity indianFoodItem) {
		//scenario1: coupoun_available
		//- make coupon available only in the months of JAN,JULY,DEC
		boolean isCoupounAvailable = calculateCouponAvailability();
		indianFoodItem.setCouponAvailable(isCoupounAvailable);
		//scenaio2: meal-card-available 
        // make meal card available only if price is more than Rs.100
		boolean mealCardAvailable = checkMealCardAvailabilty(indianFoodItem);
		indianFoodItem.setMealCardAvailable(mealCardAvailable);
		indianFoodRepo.save(indianFoodItem);
	}

	private boolean checkMealCardAvailabilty(IndianFoodEntity indianFoodItem) {
		Float price = indianFoodItem.getPrice();
		boolean isMealCardAvailable = false;
		if(price > 100 ) {
			isMealCardAvailable = true;
		}
		return isMealCardAvailable;
	}

	private boolean calculateCouponAvailability() {
		boolean isCoupounAvailable = false;
		Month month = LocalDate.now().getMonth();
		if(month.equals("JAN") || month.equals("JULY") || month.equals("DEC")) {
			isCoupounAvailable = true;
		}
		return isCoupounAvailable;
	}

	public List<IndianFoodEntity> getFoodItems() {
		return (List<IndianFoodEntity>) indianFoodRepo.findAll();
	}

	public void updateFoodItem(IndianFoodEntity indianFoodItem) {
		indianFoodRepo.save(indianFoodItem);
	}

	public void updateRating(Long id, Integer rating) {
	  IndianFoodEntity entity =	indianFoodRepo.findById(id).get();
	  entity.setRating(rating);
	  indianFoodRepo.save(entity);
	}

	public void deleteFoodItem(IndianFoodEntity indianFoodEntity) {
		indianFoodRepo.delete(indianFoodEntity);
	}

	public void deleteFoodItemById(Long id) {
		indianFoodRepo.deleteById(id);
		
	}

}
