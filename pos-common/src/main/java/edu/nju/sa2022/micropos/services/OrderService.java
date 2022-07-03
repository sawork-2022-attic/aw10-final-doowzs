package edu.nju.sa2022.micropos.services;

import edu.nju.sa2022.micropos.models.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderService {

    Flux<Order> listOrders(String userId);

    Mono<Order> findOrder(String orderId);

    Mono<Order> createOrder(String userId);

}
