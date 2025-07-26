package fr.monentreprise.write.controller;

import fr.monentreprise.write.dto.CreateOrderRequest;
import fr.monentreprise.write.model.Order;
import fr.monentreprise.write.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderCommandController {

    private final OrderRepository orderRepository;

    @PostMapping
    public Mono<Order> createOrder(@RequestBody CreateOrderRequest request) {
        Order order = new Order(null, request.getCustomerId(), "CREATED", request.getTotal());
        return orderRepository.save(order);
    }
}
