package com.clearlife.toppinginjector;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
@Component
public class UserToppingGenerator {

    private final String[] availableToppings = {"Pepperoni", "Mushrooms", "Onions", "Sausage", "Bacon", "Extra cheese", "Black olives", "Green peppers"};

    public UserToppingDto generateRandomUserTopping() {
        Random random = new Random();
        String userEmail = "user" + random.nextInt(100) + "@clearlife.com";

        Set<String> toppingNames = new HashSet<>();
        int numberOfToppings = random.nextInt(availableToppings.length);
        for (int i = 0; i < numberOfToppings; i++) {
            toppingNames.add(availableToppings[random.nextInt(availableToppings.length)]);
        }

        return new UserToppingDto(userEmail, toppingNames);
    }
}