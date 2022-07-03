package edu.nju.sa2022.micropos.services;

import edu.nju.sa2022.micropos.models.Cart;
import edu.nju.sa2022.micropos.models.Order;
import reactor.core.publisher.Mono;

public interface CartService {

    Mono<Cart> getCart(String userId);

    Mono<Cart> addItem(String userId, String productId, Integer quantity);

    Mono<Cart> removeItem(String userId, String productId);

    Mono<Double> getTotal(String userId);

    Mono<Order> checkout(String userId);

}
