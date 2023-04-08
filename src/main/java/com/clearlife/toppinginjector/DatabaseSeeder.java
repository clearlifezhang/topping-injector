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
        String dataProcessorHost = System.getenv("DATA_PROCESSOR_HOST");
        if (dataProcessorHost == null || dataProcessorHost.isEmpty()) {
            dataProcessorHost = "data-process-redis"; // Default value
        }
        String dataProcessorPortStr = System.getenv("DATA_PROCESSOR_PORT");
        int dataProcessorPort = 6060; // Default value
        if (dataProcessorPortStr != null && !dataProcessorPortStr.isEmpty()) {
            dataProcessorPort = Integer.parseInt(dataProcessorPortStr);
        }

        String apiUrl = "http://" + dataProcessorHost+ ":" + dataProcessorPort + "/redisapi/saveUserToppings";

        ResponseEntity<UserToppingDto> response = restTemplate.postForEntity(apiUrl, randomUserTopping, UserToppingDto.class);
        System.out.println("Created user topping: " + response.getBody());
    }
}
