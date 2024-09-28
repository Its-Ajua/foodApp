package com.foodapplication.FoodApp.Service;

import com.foodapplication.FoodApp.Model.Food;
import com.foodapplication.FoodApp.Model.User;
import com.foodapplication.FoodApp.repo.FoodRepo;
import com.foodapplication.FoodApp.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FoodServiceTest {

    @InjectMocks
    private FoodService foodService;  // Class being tested

    @Mock
    private FoodRepo foodRepo;        // Mocking the repository

    @Test
    public void testGetItems() {
        // Arrange
        Food food1 = new Food(1, "Pizza", "pepperoni", "20000");
        Food food2 = new Food(2,"Burger", "chicken burger","5000");

        List<Food> mockFoodList = Arrays.asList(food1, food2);
        when(foodRepo.findAll()).thenReturn(mockFoodList);  // Mock the findAll operation

        // Act
        List<Food> foodList = foodService.getItems();

        // Assert
        assertNotNull(foodList);                            // Verify that the list is not null
        assertEquals(2, foodList.size());                   // Ensure the correct number of items
        verify(foodRepo, times(1)).findAll();               // Verify findAll was called
    }

    @Test
    public void testAddItems() {
        // Arrange
        Food food = new Food(3, "Sushi", "Fresh and flavorful Japanese delicacy", "15000");
        when(foodRepo.save(any(Food.class))).thenReturn(food);  // Mock the save operation

        // Act
        Food savedFood = foodService.addItems(food);

        // Assert
        assertNotNull(savedFood);                               // Verify that the item is saved
        assertEquals("Sushi", savedFood.getFood_name());             // Ensure name is correct
        verify(foodRepo, times(1)).save(food);                  // Verify save was called
    }
}


