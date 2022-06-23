package com.paytm.food.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import  com.paytm.food.entities.IndianFoodEntity;

@Repository
public interface IndianFoodRepository extends CrudRepository<IndianFoodEntity,Long>{

}