package com.foodapplication.FoodApp.Controller;

import com.foodapplication.FoodApp.Model.Food;
import com.foodapplication.FoodApp.Model.User;
import com.foodapplication.FoodApp.Service.FoodService;
import com.foodapplication.FoodApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FoodService foodService;



    @PostMapping("/api/users")
    public ResponseEntity<User> addUsers( @Validated @RequestBody User user) {
        User savedUser = userService.addUsers(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public String Login( @Validated @RequestBody User user) {
        return userService.verify(user);
    }

    @GetMapping("/login/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/login/addItems")
    public ResponseEntity<Food> addItems(@Validated @RequestBody Food food) {
        Food savedItem = foodService.addItems(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @GetMapping("/login/getItems")
    public ResponseEntity<List<Food>> getItems() {
        return new ResponseEntity<List<Food>>(foodService.getItems(), HttpStatus.OK);
    }
}
