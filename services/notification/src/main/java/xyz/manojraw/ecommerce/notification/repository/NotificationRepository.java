package xyz.manojraw.ecommerce.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.manojraw.ecommerce.notification.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
