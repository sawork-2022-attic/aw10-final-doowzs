package edu.nju.sa2022.micropos.services.rest;

import edu.nju.sa2022.micropos.models.Cart;
import edu.nju.sa2022.micropos.models.Order;
import edu.nju.sa2022.micropos.services.CartService;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class CartServiceRestImpl implements CartService {

    private final WebClient webClient;

    public CartServiceRestImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Cart> getCart(String userId) {
        throw new UnsupportedOperationException("not implemented in rest impl");
    }

    @Override
    public Mono<Cart> addItem(String userId, String productId, Integer quantity) {
        throw new UnsupportedOperationException("not implemented in rest impl");
    }

    @Override
    public Mono<Cart> removeItem(String userId, String productId) {
        throw new UnsupportedOperationException("not implemented in rest impl");
    }

    @Override
    public Mono<Double> getTotal(String userId) {
        throw new UnsupportedOperationException("not implemented in rest impl");
    }

    @Override
    public Mono<Order> checkout(String userId) {
        return webClient.post()
                .uri(String.format("http://cart-service/raw/carts/%s/checkout", userId))
                .retrieve()
                .bodyToMono(Order.class);
    }

}
