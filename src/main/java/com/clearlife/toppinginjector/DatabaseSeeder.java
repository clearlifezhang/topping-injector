package com.clearlife.toppinginjector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserToppingGenerator userToppingGenerator;

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
    }

    @Scheduled(fixedRate = 60000) // Seed data every 60 seconds
    public void seedDatabase() {
        UserToppingDto randomUserTopping = userToppingGenerator.generateRandomUserTopping();

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:6060/redisapi/saveUserToppings"; // Replace with the appropriate API URL

        ResponseEntity<UserToppingDto> response = restTemplate.postForEntity(apiUrl, randomUserTopping, UserToppingDto.class);
        System.out.println("Created user topping: " + response.getBody());
    }
}
