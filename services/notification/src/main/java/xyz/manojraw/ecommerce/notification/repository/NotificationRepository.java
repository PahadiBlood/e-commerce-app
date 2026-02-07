package xyz.manojraw.ecommerce.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.manojraw.ecommerce.notification.model.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, Long> {
}
