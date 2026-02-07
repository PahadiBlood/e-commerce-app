package xyz.manojraw.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZoneId;

@EnableFeignClients
@EnableJpaAuditing
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        System.out.println("ZoneId.systemDefault() = " + ZoneId.systemDefault());
        System.out.println("user.timezone = " + System.getProperty("user.timezone"));

        SpringApplication.run(OrderApplication.class, args);
    }

}
