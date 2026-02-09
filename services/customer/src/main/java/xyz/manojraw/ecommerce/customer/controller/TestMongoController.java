package xyz.manojraw.ecommerce.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMongoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/api/v1/customers/test-mongo")
    public String testMongo() {
        try {
            String dbName = mongoTemplate.getDb().getName();
            long count = mongoTemplate.getCollection("test").countDocuments();
            return "Connected to DB: " + dbName + ", Collections count: " + count;
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}