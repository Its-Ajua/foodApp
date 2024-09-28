package com.foodapplication.FoodApp.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Food_Table")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "food_name", unique = true, nullable = false)
    private String food_name;

    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @Column(name = "price", unique = true, nullable = false)
    private String price;
}
