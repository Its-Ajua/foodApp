package com.foodapplication.FoodApp.Service;


import com.foodapplication.FoodApp.Model.Food;
import com.foodapplication.FoodApp.repo.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    FoodRepo foodRepo;

    public List<Food> getItems() {
        return foodRepo.findAll();
    }

    public Food addItems(Food food) {
        return foodRepo.save(food);
    }
}
