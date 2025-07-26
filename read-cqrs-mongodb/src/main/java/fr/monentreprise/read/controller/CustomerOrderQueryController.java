package fr.monentreprise.read.controller;

import fr.monentreprise.read.model.CustomerOrderView;
import fr.monentreprise.read.repository.CustomerOrderViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer-orders")
@RequiredArgsConstructor
public class CustomerOrderQueryController {

    private final CustomerOrderViewRepository repository;

    @GetMapping
    public Flux<CustomerOrderView> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{customerId}")
    public Mono<CustomerOrderView> getById(@PathVariable String customerId) {
        return repository.findByCustomerId(customerId);
    }
}
