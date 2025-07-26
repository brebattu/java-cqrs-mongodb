package fr.monentreprise.write.repository;

import fr.monentreprise.write.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {}
