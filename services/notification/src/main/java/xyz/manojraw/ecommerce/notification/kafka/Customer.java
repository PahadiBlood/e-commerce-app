package xyz.manojraw.ecommerce.notification.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}

