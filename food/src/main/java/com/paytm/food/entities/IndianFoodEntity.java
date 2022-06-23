package com.paytm.food.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="indian-food")
public class IndianFoodEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(name="food_name")
    String foodName;
    
	@Column(name = "price")
    Float price;
	
	@Column(name = "rating")
	Integer rating;
	
	@Column(name="coupon_available")
	boolean couponAvailable;
	
	@Column(name="meal-card-available")
    boolean mealCardAvailable;
	
	public Long getId() {
		return id;
	}

	public boolean isCouponAvailable() {
		return couponAvailable;
	}

	public void setCouponAvailable(boolean couponAvailable) {
		this.couponAvailable = couponAvailable;
	}

	public boolean isMealCardAvailable() {
		return mealCardAvailable;
	}

	public void setMealCardAvailable(boolean mealCardAvailable) {
		this.mealCardAvailable = mealCardAvailable;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
}