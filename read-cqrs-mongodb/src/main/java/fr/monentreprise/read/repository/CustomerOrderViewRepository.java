package fr.monentreprise.read.repository;

import fr.monentreprise.read.model.CustomerOrderView;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerOrderViewRepository extends ReactiveMongoRepository<CustomerOrderView, String> {
    Flux<CustomerOrderView> findAll();
    Mono<CustomerOrderView> findByCustomerId(String customerId);
}
