package com.foodapplication.FoodApp.repo;

import com.foodapplication.FoodApp.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {
}
